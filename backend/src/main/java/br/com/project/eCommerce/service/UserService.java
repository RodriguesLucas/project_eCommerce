package br.com.project.eCommerce.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
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

	public UserReturnDTO createUser(Map<String, String> body) {
		UserDTO user = new UserDTO();
		user.setName(body.get("name"));
		user.setPassword(body.get("password"));
		user.setAdmin(false);
		
		System.out.println("createUser() Start UserDTO: " + user.toString());
		if (!checkExistingUser(user).isEmpty()) {
			return new UserReturnDTO(false);

		} else if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
			return new UserReturnDTO(false);
		}
		
		user.setPassword(encryptedPassword(user.getPassword()));
		
		userRepository.save(new UserEntity(user));
		return new UserReturnDTO(true);
	}

	public UserReturnDTO validateUser(String user, String password) {
		
		Optional<UserEntity> optional = userRepository.findByNameAndPassword(user, encryptedPassword(password));
		UserReturnDTO dto = new UserReturnDTO();

		if (!optional.isEmpty()) {
			dto.setValid(true);
			dto.setAdmin(checkIsAdmin(optional.get()));
			return dto;
		}

		dto.setAdmin(false);
		dto.setValid(false);
		return dto;
	}

	private boolean checkIsAdmin(UserEntity userEntity) {
		return userEntity.getIsAdmin();
	}

	private Optional<UserEntity> checkExistingUser(UserDTO user) {
		Optional<UserEntity> optional = userRepository.findByName(user.getName());
		return optional;
	}

	private String encryptedPassword(String password) {
		StringBuilder hexString = new StringBuilder();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

			byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return hexString.toString();
	}

}
