package pti.sb_squash_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.Roles;
import pti.sb_squash_mvc.model.User;

@Controller
public class AppController {
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/createpwd")
	public String createNewPwd() {
		return "createnewpwd";
	}
	
	@GetMapping("/showadminpage")
	public String showAdminPage() {
		return "admin";
	}
	
	@GetMapping("/showUsers")
	public String showUsers() {
		return "user";
	}
	
}
