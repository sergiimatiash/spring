package sergei.webservice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import sergei.webservice.models.CryptoCurrencyRepository;
import sergei.webservice.models.User;
import sergei.webservice.models.UsersRepository;

@Controller
public class UserContoller {
	final UsersRepository usersRepo;
	
	public UserContoller(UsersRepository usersRepository) {
		this.usersRepo = usersRepository;
		
	}
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) {   
		 Map<String, String> model = new HashMap<>();
		if (session.getValue("name")!=null)
		 {
			 model.put("name", (String) session.getValue("name"));
		     
			 return new ModelAndView("login",model);
		 }
	    return new ModelAndView("login", model);
	    
	}
	
	@PostMapping("/login")
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response,HttpSession session, @RequestBody User user) {   
		 Map<String, String> model = new HashMap<>();
		if (session.getValue("name")!=null)
		 {
			 model.put("name", (String) session.getValue("name"));
		     
			 return new ModelAndView("login",model);
		 }
	    
	    List<User> array = usersRepo.findAll();
		  for (User i:array) {
	           if (i.name == user.name)
	        	   if (user.password == i.password)
	        	   {	        		 
	        		    model.put("name", user.name);
	        		    session.setAttribute("name", user.name);
	        	        return new ModelAndView("login", model);
	        	   }
	           }
		  
	    response.setStatus(404);
	    return new ModelAndView("login", model);
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {   
		
		 Map<String, String> model = new HashMap<>();
		 session.invalidate();
	    return new ModelAndView("logout", model);
	}
	
	@GetMapping("/register")
	public ModelAndView register() {   
		Map<String, String> model = new HashMap<>();
	    model.put("name", "Alexey");
	    return new ModelAndView("register", model);
	}
	
	@GetMapping("/register/default")
	public ResponseEntity registerDefault() {   
		User user = new User();
		user.name = "guest";
		user.password = "guest";
		 try {
			  usersRepo.save(user);
		  }catch(Exception c) {
			  return new ResponseEntity(HttpStatus.BAD_REQUEST);
		  }
		
		  return new ResponseEntity(HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {   
		 try {
			  usersRepo.save(user);
		  }catch(Exception c) {
			  return new ResponseEntity(HttpStatus.BAD_REQUEST);
		  }
		
		  return new ResponseEntity(HttpStatus.OK);
	}
}
