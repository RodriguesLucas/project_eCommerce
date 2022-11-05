package br.com.project.eCommerce.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.project.eCommerce.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String password;
	
	public UserEntity() {

	}
	
	public UserEntity(UserDTO user) {
		this.name = user.getName();
		this.password = user.getPassword();
	}


}
