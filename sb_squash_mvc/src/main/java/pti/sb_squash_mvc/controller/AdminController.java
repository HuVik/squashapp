package pti.sb_squash_mvc.controller;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.Roles;
import pti.sb_squash_mvc.model.User;
import pti.sb_squash_mvc.xmlparser.XmlParser;

@Controller
public class AdminController {
	
	
	public void getAllUser(Model model){
		
		Database db = new Database();
		List<User>users = db.getAllUser();
		
		if(users != null) {
			model.addAttribute("users", users);
		}else {
			model.addAttribute("queryfail", "Nincsenek felhasználók az adatbázisban!");
		}
		
		
	}
	
	
	@PostMapping("/welcomepage")
	public String loginByAdmin(Model model, 
			@RequestParam(name="username") String userName,
			@RequestParam(name="userpwd") String userPwd) {
		String targetPage = "";
		
		Database db = new Database();
		
		if((!userName.isEmpty()) && (!userPwd.isEmpty())) {
			User user = db.getUserByNameAndPwd(userName,userPwd); 
			if((user != null)) {
				if(user.getRole().equals(Roles.ADMIN)) {
					getAllUser(model);
					targetPage = "admin";
					
				}else {
					
					user.setEntered(true);
					int entryCounter = user.getPieceOfEntry();
					if(user.isEntered()){
						user.setPieceOfEntry(entryCounter+1);
					}else {
						user.setEntered(false);
					}
					db.updateUser(user);
					
					if(entryCounter == 2) {
						targetPage="createnewpwd";
					}else {
						targetPage = "user";
					}
					
				}
				
			}else {
				model.addAttribute("denied", "Nem létező felhasználó!");
				targetPage = "login";
			}
		}else {
			model.addAttribute("error", "A beviteli mezők kitöltése javasolt!");
			targetPage = "login";
		}
		
		return targetPage;
	}
	
	@PostMapping("/regbyadmin")
	public String regByAdmin(Model model, 
			@RequestParam(name="username") String userName,
			@RequestParam(name="role") String role) {
		
		Database db = new Database();
		String genPass = db.genPass();
		User user = new User();
		user.setName(userName);
		user.setPwd(genPass);
		if(role.equals("ADMIN")) {
			user.setRole(Roles.ADMIN);
			getAllUser(model);
		}else {
			user.setRole(Roles.PLAYER);
			model.addAttribute("success", "A felhasználó sikeresen regisztrálva!");
		}
		
		user.setEntered(false);
		user.setPieceOfEntry(0);
		db.saveUser(user);
		
		return "admin";
	}
	
		@PostMapping("/reglocationbyadmin")
		public String regLocationByAdmin(Model model,
				@RequestParam(name = "locaddress") String locAddress) throws JDOMException, IOException {
			
				Database db = new Database();
				double rentPerHour = XmlParser.getRate();
				
				if((!locAddress.isEmpty()) && (rentPerHour > 0)) {
					Location location = db.getLocationByAddress(locAddress);
					
					if(location == null) {
						location = new Location();
						location.setAddress(locAddress);
						location.setRentPerHour(rentPerHour);
						db.saveLocation(location);
						model.addAttribute("done", "Helyszín sikeresen regisztrálva!");
						
					}else {
						model.addAttribute("alert", "A regisztrálni kívánt helyszín már létezik az adatbázisban!");
					}
					
				}else {
					model.addAttribute("fail", "A mezők kitöltése szükséges az adatrögzítéshez!");
				}
				getAllUser(model);
			return "admin";
		}
		
		@PostMapping("/regmatchbyadmin")
		public String regMatchByAdmin(Model model, 
				@RequestParam(name ="hplayerid") int hPlayerId,
				@RequestParam(name ="aplayerid") int aPlayerId,
				@RequestParam(name="locationid") int locationId) {
			
			Database db = new Database();
			
			if((hPlayerId > 0) && (aPlayerId > 0) && (locationId > 0)) {
				//If I want to define which data is not exists in database then I need to check them.
				User hPlayer = db.getUserById(hPlayerId);
				User aPlayer = db.getUserById(aPlayerId);
				Location location = db.getLocationById(locationId);
					if(hPlayer == null) {
						model.addAttribute("hplayernull", "A hazai játékos nem szerepel a rendszerben!");
					}else if(aPlayer == null) {
						model.addAttribute("aplayernull", "A vendég játékos nem szerepel a rendszerben!");
					}
					else if(location == null) {
						model.addAttribute("locationisnull", "A helyszín nem szerepel a rendszerben!");
					}
					else {
						Match match = new Match();
						match.setaPlayer(aPlayer);
						match.setaPlayerPoints(0);
						match.sethPlayer(hPlayer);
						match.sethPlayerPoints(0);
						match.setLocation(location);
						match.setDate();
						db.saveMatch(match);
						model.addAttribute("success", "A mérkőzés sikeresen rögzítve");
						
					}
				
			}else {
				model.addAttribute("warning", "Mezők kitöltése kötelező!");
			}
			
			getAllUser(model);
			return "admin";
		}
		
		
		

}
