package br.com.project.ecommerce;

public class UserReturnDTO {
    private String isValid;

    private String isAdmin;

    public UserReturnDTO(String value) {
        this.isValid = value;
    }

    public UserReturnDTO() {

    }

    public String isValid() {
        return isValid;
    }

    public void setValid(String isValid) {
        this.isValid = isValid;
    }

    public String isAdmin() {
        return isAdmin;
    }

    public void setAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
