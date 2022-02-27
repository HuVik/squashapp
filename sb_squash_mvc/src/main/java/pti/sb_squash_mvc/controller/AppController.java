package pti.sb_squash_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Roles;
import pti.sb_squash_mvc.model.User;

@Controller
public class AppController {
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("createpwd")
	public String createNewPwd() {
		return "createnewpwd";
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
					targetPage = "admin";
				}else {
					targetPage = "user";
					user.setEntered(true);
					int entryCounter = user.getPieceOfEntry();
					user.setPieceOfEntry(entryCounter+1);
					if(entryCounter > 0) {
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
	
	@PostMapping("/showchangepassword")
	public String changePassword(Model model, 
			@RequestParam(name = "username") String userName,
			@RequestParam(name = "oldpwd") String oldPwd,
			@RequestParam(name="userspassword") String usersPwd,
			@RequestParam(name="newuserspassword") String newUsersPwd) {
		
			Database db = new Database();
			
			if((!userName.isEmpty()) && (!oldPwd.isEmpty()) && (!usersPwd.isEmpty()) && (!newUsersPwd.isEmpty())) {
				User user = db.getUserByNameAndPwd(userName, oldPwd);
				if(user != null) {
					if(!user.getPwd().equals(newUsersPwd) && (!user.getPwd().equals(usersPwd))) {
						//save user width his/her new password
						user.setId(user.getId());
						user.setName(userName);
						user.setPwd(newUsersPwd);
						user.setRole(user.getRole());
						user.setEntered(user.isEntered());
						user.setPieceOfEntry(user.getPieceOfEntry());
						db.updateUser(user);
						model.addAttribute("success", "A jelszó sikeresen módosítva!");
					}else {
						//password is exists
						model.addAttribute("warning", "Az új jelszó nem egyezhet meg a régivel!");
					}
				}else {
					model.addAttribute("nofound", "A megadott felhasználó nem létezik a rendszerben!");
					
				}
			}else {
				model.addAttribute("error", "Mezők kitöltése kötelező!");
			}
			
		
		return "createnewpwd";
	}
	
}
