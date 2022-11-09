package br.com.project.eCommerce.dtos;

public class UserReturnDTO {

	private boolean isValid;

	private boolean isAdmin;

	public UserReturnDTO(boolean value) {
		this.isValid = value;
	}

	public UserReturnDTO() {
		
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
