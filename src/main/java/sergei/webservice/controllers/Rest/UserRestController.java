package sergei.webservice.controllers.Rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sergei.webservice.models.User;
import sergei.webservice.models.UsersRepository;

@RestController
class UserRestController {

	private final UsersRepository repository;

	UserRestController(UsersRepository repository) {
		this.repository = repository;
	}

	

	@GetMapping("/rest/users")
	List<User> all() {
		return (List<User>) repository.findAll();
	}

	@PostMapping("/rest/users")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	
	@GetMapping("/rest/users/{id}")
	Optional<User> one(@PathVariable Long id) {

		return repository.findById(id);
			
	}

	@PutMapping("/rest/users/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

		return repository.findById(id)
			.map(User -> {
				User.name = (newUser.name);
				
				return repository.save(User);
			})
			.orElseGet(() -> {
				newUser.id = id;
				return repository.save(newUser);
			});
	}

	@DeleteMapping("/rest/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}