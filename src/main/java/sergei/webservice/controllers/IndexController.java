package sergei.webservice.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sergei.webservice.models.CryptoCurrency;
import sergei.webservice.models.CryptoCurrencyRepository;
import sergei.webservice.models.UsersRepository;

@Controller
public class IndexController {
final UsersRepository usersRepo;
final CryptoCurrencyRepository cryptoRepo;
public IndexController(UsersRepository usersRepository,CryptoCurrencyRepository cryptoRepository) {
	this.usersRepo = usersRepository;
	this.cryptoRepo = cryptoRepository;
}

@GetMapping("/")
public ModelAndView index() {   
	Map<String, String> model = new HashMap<>();
    model.put("name", "Alexey");
    return new ModelAndView("index", model);
}

}
