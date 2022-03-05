package pti.sb_squash_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.Roles;
import pti.sb_squash_mvc.model.User;

@Controller
public class AdminController {
	
	
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
					targetPage = "admin";
				}else {
					targetPage = "user";
					user.setEntered(true);
					int entryCounter = user.getPieceOfEntry();
					user.setPieceOfEntry(entryCounter+1);
					if(entryCounter > 1) {
						targetPage = "user";
					}else {
						targetPage="createnewpwd";
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
		}else {
			user.setRole(Roles.PLAYER);
			model.addAttribute("success", "A felhasználó sikeresen regisztrálva!");
		}
		
		user.setEntered(false);
		user.setPieceOfEntry(0);
		db.saveUser(user);
		
		return "admin";
	}
	
	//helyszín rögzítésnél tartok
		@PostMapping("/reglocationbyadmin")
		public String regLocationByAdmin(Model model,
				@RequestParam(name = "locaddress") String locAddress,
				@RequestParam(name="rentperhour") int rentPerHour) {
			
				Database db = new Database();
				
				if((!locAddress.isEmpty()) && (rentPerHour > 0)) {
					Location location = new Location();
					location.setAddress(locAddress);
					location.setRentPerHour(rentPerHour);
					db.saveLocation(location);
					model.addAttribute("done", "Helyszín sikeresen regisztrálva!");
				}else {
					model.addAttribute("fail", "A mezők kitöltése szükséges az adatrögzítéshez!");
				}
				
			return "admin";
		}

}
