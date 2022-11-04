package br.com.project.eCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {

	private String name;

	private String password;
}
