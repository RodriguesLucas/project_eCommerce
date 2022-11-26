package br.com.project.ecommerce.dtos;

public class SalesDTO {
    private String productName;

    private String userName;

    public SalesDTO() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
