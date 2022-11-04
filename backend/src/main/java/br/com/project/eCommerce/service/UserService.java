package br.com.project.eCommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.eCommerce.dtos.UserDTO;
import br.com.project.eCommerce.entities.UserEntity;
import br.com.project.eCommerce.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public boolean createUser(UserDTO user) {
		if (!checkExistingUser(user)) {
			return false;
		}
		String password = encryptingPassword(user);
		 
		return true;
	}
	
	private String encryptingPassword(UserDTO user) {
		BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
		return criptografar.encode(user.getPassword());
	}

	private boolean checkExistingUser(UserDTO user) {
		Optional<UserEntity> optional = userRepository.findByName(user.getName());
		return optional.isEmpty();
	}

}
