package pti.sb_squash_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.User;

@Controller
public class UserController {
	
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
