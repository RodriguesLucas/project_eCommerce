package br.com.project.eCommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.eCommerce.dtos.UserDTO;
import br.com.project.eCommerce.dtos.UserReturnDTO;
import br.com.project.eCommerce.entities.UserEntity;
import br.com.project.eCommerce.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserReturnDTO createUser(UserDTO user) {
		if (!checkExistingUser(user)) {
			return new UserReturnDTO(false);
		}
		userRepository.save(new UserEntity(user));
		return new UserReturnDTO(true);
	}

	public UserReturnDTO validateUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkExistingUser(UserDTO user) {
		Optional<UserEntity> optional = userRepository.findByName(user.getName());
		return optional.isEmpty();
	}

}
