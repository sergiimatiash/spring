package sergei.webservice.controllers.Rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sergei.webservice.models.CryptoCurrency;
import sergei.webservice.models.CryptoCurrencyRepository;

@RestController
class CryptoCurrencyRestController {

	private final CryptoCurrencyRepository repository;

	CryptoCurrencyRestController(CryptoCurrencyRepository repository) {
		this.repository = repository;
	}



	@GetMapping("/rest/crypto")
	List<CryptoCurrency> all() {
		return (List<CryptoCurrency>) repository.findAll();
	}

	@PostMapping("/rest/crypto")
	CryptoCurrency newCryptoCurrency(@RequestBody CryptoCurrency newCryptoCurrency) {
		return repository.save(newCryptoCurrency);
	}

	
	@GetMapping("/rest/Crypto/{id}")
	Optional<CryptoCurrency> one(@PathVariable Long id) {

		return repository.findById(id);
			
	}

	@PutMapping("/rest/crypto/{id}")
	CryptoCurrency replaceCryptoCurrency(@RequestBody CryptoCurrency newCryptoCurrency, @PathVariable Long id) {

		return repository.findById(id)
			.map(CryptoCurrency -> {
				CryptoCurrency.setName(newCryptoCurrency.getName());
				
				return repository.save(CryptoCurrency);
			})
			.orElseGet(() -> {
				newCryptoCurrency.setId(id);
				return repository.save(newCryptoCurrency);
			});
	}

	@DeleteMapping("/rest/crypto/{id}")
	void deleteCryptoCurrency(@PathVariable Long id) {
		repository.deleteById(id);
	}
}