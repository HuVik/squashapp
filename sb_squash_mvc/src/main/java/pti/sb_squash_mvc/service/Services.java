package pti.sb_squash_mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.ui.Model;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.User;

public class Services {
	
	
	public static void getAllUser(Model model){
		
		Database db = new Database();
		List<User>users = db.getAllUser();
		
			if(users != null) {
				model.addAttribute("users", users);
			}else {
				model.addAttribute("queryfail", "Nincsenek felhasználók az adatbázisban!");
			}
		
		
	}

	public static void showMacthes(Model model) {
	
	Database db = new Database();
	
	List<Match> matches = db.getAllMacthByDate();
	
	
		if(matches != null) {
			
			model.addAttribute("matches", matches);
		}else {
			model.addAttribute("error", "Nem létezik a rendszerben a kívánt mérkőzés!");
		}
	
	
	}
	
	public static String genPass() {
    	String password = "";
    	
    	Random rand = new Random();
        String upperCase = "QWERTZUIOPASDFGHJKLYXCVBNM";
        String lowerCase = "qwertzuiopasdfghjklyxcvbnm";
        String numbers = "0123456789";
        String spec = "%-#_$@?[]{}()";
        String all = upperCase + lowerCase + numbers + spec;
        ArrayList<Character> letterList = new ArrayList<Character>();
        
        for (char c : all.toCharArray()) {
			letterList.add(c);
		}
        
        int charCounter = 0;
        while(charCounter <8) {
        	int randomNumber = rand.nextInt(75)+1;
        	for (int i = 0; i < letterList.size(); i++) {
        		if(randomNumber == letterList.get(i)) {
        			password += letterList.get(i).charValue();
        			charCounter++;
        		}
        		
    		}
        	
        }
       
    	return password;
    }
	
	

}
