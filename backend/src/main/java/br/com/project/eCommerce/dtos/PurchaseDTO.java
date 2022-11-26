package br.com.project.eCommerce.dtos;

public class PurchaseDTO {

	private boolean sucess;
	
	private Double sum;

	public PurchaseDTO() {

	}

	public PurchaseDTO(boolean isSucess) {
		this.sucess = isSucess;
	}

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean isSucess) {
		this.sucess = isSucess;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

}
