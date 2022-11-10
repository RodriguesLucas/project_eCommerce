package br.com.project.eCommerce.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		if (!checkExistingUser(user).isEmpty()) {
			return new UserReturnDTO(false);

		} else if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
			return new UserReturnDTO(false);
		}

		encryptedPassword(user);

		userRepository.save(new UserEntity(user));
		return new UserReturnDTO(true);
	}

	public UserReturnDTO validateUser(UserDTO user) {
		encryptedPassword(user);
		Optional<UserEntity> optional = userRepository.findByNameAndPassword(user.getName(), user.getPassword());
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

	private void encryptedPassword(UserDTO user) {
		StringBuilder hexString = new StringBuilder();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

			byte messageDigest[] = algorithm.digest(user.getPassword().getBytes("UTF-8"));

			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		user.setPassword(hexString.toString());
	}

}
