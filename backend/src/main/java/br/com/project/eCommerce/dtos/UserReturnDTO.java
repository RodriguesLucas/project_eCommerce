package br.com.project.eCommerce.dtos;

public class UserReturnDTO {

	private boolean isValid;
	
	public UserReturnDTO(boolean value) {
		this.isValid = value;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	

}
