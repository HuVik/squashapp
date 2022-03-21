package pti.sb_squash_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Location;

@Controller
public class AppController {
	
	@GetMapping("/")
	public String login(Model model) {
		AdminController ad = new AdminController();
		ad.getAllUser(model);
		return "login";
	}
	
	@PostMapping("findlocations")
	public String showLocations(Model model, @RequestParam(name="locationinput") String locationInput) {
		
		Database db = new Database();
		AdminController ad = new AdminController();
		
		if(locationInput != null) {
			Location location = db.getLocationByAddress(locationInput);
			if((location != null)) {
				model.addAttribute("foundlocation", location.getAddress());
			}else {
				model.addAttribute("alert", "A keresett helyszínre nincs találat a rendszerben!");
			}
		}else {
			model.addAttribute("warning", "A kereséshez töltse ki a mezőt!");
		}
		
		ad.getAllUser(model);
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
	
	@GetMapping("/showeverything")
	public String showUsers() {
		return "user";
	}
	
}
