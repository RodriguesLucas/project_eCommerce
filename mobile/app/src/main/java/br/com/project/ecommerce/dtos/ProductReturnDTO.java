package br.com.project.ecommerce.dtos;

public class ProductReturnDTO {
    private boolean sucess;

    public ProductReturnDTO(boolean sucess) {
        this.sucess = sucess;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

}
