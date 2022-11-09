package br.com.project.eCommerce.dtos;

public class PurchaseDTO {

	private boolean isSucess;

	public PurchaseDTO() {

	}

	public PurchaseDTO(boolean isSucess) {
		this.isSucess = isSucess;
	}

	public boolean isSucess() {
		return isSucess;
	}

	public void setSucess(boolean isSucess) {
		this.isSucess = isSucess;
	}

}
