package sergei.webservice.controllers;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sergei.webservice.models.CryptoCurrency;
import sergei.webservice.models.CryptoCurrencyRepository;
import sergei.webservice.models.UsersRepository;
@Controller
public class CryptoCurrencyController {
	final UsersRepository usersRepo;
	final CryptoCurrencyRepository cryptoRepo;
	public CryptoCurrencyController(UsersRepository usersRepository,CryptoCurrencyRepository cryptoRepository) {
		this.usersRepo = usersRepository;
		this.cryptoRepo = cryptoRepository;
	}
	
	@GetMapping("crypto/default")
	public String generateDefault() {   
		CryptoCurrency crypto  = new CryptoCurrency();
		crypto.name = "BitCoin";
		crypto.price = (long) 3600;
		crypto.description="deafsafsdgsadg";
	    cryptoRepo.save(crypto);
	    return "";
	}
	
	@GetMapping("crypto/add")
	public ModelAndView cryptoAdd() {   		
		    ModelAndView modelAndView = new ModelAndView("create_product");
		    modelAndView.addObject("currency", false);
		    return modelAndView;
	}
	
	
	@RequestMapping(value = "/crypto/add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String cryptoAddPost( CryptoCurrency currency) {   		
		 try {
			  cryptoRepo.save(currency);
		  }catch(Exception c) {
			  
		  }
		
		 return "redirect:/products/list";
		   
	}
	
	@GetMapping("/products")
	public ModelAndView cryptoCurrency() {  
	    Iterable<CryptoCurrency> model = cryptoRepo.findAll();
	    ModelAndView modelAndView  = new ModelAndView("products");
	    modelAndView.addObject("currencies", model);
	    return modelAndView;
	}
	
	@GetMapping("/products/list")
	public ModelAndView cryptoCurrencyList() {   
	    Iterable<CryptoCurrency> model = cryptoRepo.findAll();
	    ModelAndView modelAndView  = new ModelAndView("products_list");
	    modelAndView.addObject("currencies", model);
	    return modelAndView;
	}
	
	@GetMapping("/crypto/delete/{id}")
	public String deleteCryptoCurrencyById(@PathVariable("id")Long id) {   
	  try {
		  cryptoRepo.deleteById(id);
	  }catch(Exception c) {
		  
	  }
	
	  return "redirect:/products/list";
	}

	@GetMapping("/crypto/{id}")
	public ModelAndView cryptoCurrencyById(@PathVariable("id")Long id) {   
	    Optional<CryptoCurrency> model = cryptoRepo.findById(id);
	   
	    ModelAndView modelAndView  = new ModelAndView("create_product");
	    modelAndView.addObject("currency",  model.get());
	    return modelAndView;
	}
}
