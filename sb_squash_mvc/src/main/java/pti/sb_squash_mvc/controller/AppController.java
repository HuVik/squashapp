package pti.sb_squash_mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.Roles;
import pti.sb_squash_mvc.model.User;
import pti.sb_squash_mvc.service.Services;


@Controller
public class AppController {
	
	
	@GetMapping("/")
	public String login(Model model) {
		
		
		Services.getAllUser(model);
		
		return "login";
	}
	
	@PostMapping("findlocations")
	public String showLocations(Model model, @RequestParam(name="locationinput") String locationInput) {
		
		Database db = new Database();
		
		if(locationInput != null) {
			Location location = db.getLocationByAddress(locationInput);
			if((location != null)) {
				model.addAttribute("msg", "A választott helyszín: ");
				model.addAttribute("foundlocation", location.getAddress());
			}else {
				model.addAttribute("alert", "A keresett helyszínre nincs találat a rendszerben!");
			}
		}else {
			model.addAttribute("warning", "A kereséshez töltse ki a mezőt!");
		}
		
		Services.getAllUser(model);
		return "login";
	}
	
	@PostMapping("/welcomepage")
	public String loginToWelcomePage(Model model, 
			@RequestParam(name="username") String userName,
			@RequestParam(name="userpwd") String userPwd) {
		String targetPage = "";
		
		Database db = new Database();
		Services.getAllUser(model);
		
		if((!userName.isEmpty()) && (!userPwd.isEmpty())) {
			User user = db.getUserByNameAndPwd(userName,userPwd); 
			if((user != null)) {
				if(user.getRole().equals(Roles.ADMIN)) {
					targetPage = "admin";
					user.setEntered(true);
				}else {
					
					user.setEntered(true);
					int entryCounter = user.getPieceOfEntry();
					
					if((user.isEntered()) && (user.getRole().equals(Roles.PLAYER))){
						user.setPieceOfEntry(entryCounter+1);
					}
					
					if(entryCounter == 2) {
						targetPage="createnewpwd";
					}else {
						
						model.addAttribute("username", user.getName());
						Services.showMacthes(model);
						targetPage = "user";
						
					}
					
					if(((user.getRole().equals(Roles.ADMIN)) && (!user.isEntered())) 
							|| ((user.getRole().equals(Roles.PLAYER)) && (!user.isEntered()))) {
						user.setEntered(false);
					}
					db.updateUser(user);
					
				}
				
				
			}else {
				model.addAttribute("denied", "Nem létező felhasználó!");
				targetPage = "login";
			}
		}else {
			model.addAttribute("error", "A beviteli mezők kitöltése javasolt!");
			targetPage = "login";
		}
		
		Services.getAllUser(model);
		return targetPage;
	}
	
	
	@GetMapping("/createpwd")
	public String createNewPwd() {
		return "createnewpwd";
	}
	
	@GetMapping("/showadminpage")
	public String showAdminPage() {
		return "admin";
	}
	
	@GetMapping("/showeverything")
	public String showUsers() {
		return "user";
	}
	
	
	@PostMapping("/cancel")
	public String cancel(Model model) {
		Database db = new Database();
		
		List<User> users = db.getAllUser();
		for (int i = 0; i < users.size(); i++) {
			User currentUser = users.get(i);
			if((currentUser.isEntered()) && (currentUser.getRole().equals(Roles.PLAYER)) || (currentUser.isEntered()) && (currentUser.getRole().equals(Roles.ADMIN))) {
				currentUser.setEntered(false);
				db.updateUser(currentUser);
			}
		}
		Services.getAllUser(model);
		return "login";
	}
	
}
