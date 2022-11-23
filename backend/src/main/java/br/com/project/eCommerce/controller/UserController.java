package br.com.project.eCommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.eCommerce.dtos.UserDTO;
import br.com.project.eCommerce.dtos.UserReturnDTO;
import br.com.project.eCommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public UserReturnDTO createUser(@RequestParam Map<String, String> body ) {
		return userService.createUser(body);
	}

	@GetMapping("/validate/{user}/{password}")
	public UserReturnDTO validateUser(@PathVariable String user, @PathVariable String password) {
		return userService.validateUser(user, password);
	}

}
