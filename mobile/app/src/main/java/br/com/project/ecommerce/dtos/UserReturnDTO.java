package br.com.project.ecommerce.dtos;

public class UserReturnDTO {
    private boolean valid;

    private boolean admin;

    public UserReturnDTO(boolean value) {
        this.valid = value;
    }

    public UserReturnDTO() {

    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean isValid) {
        this.valid = isValid;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }
}
