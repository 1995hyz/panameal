package Application.model;

public class LoginForm {
    String email;
    String passwordHash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LoginForm(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }
    public LoginForm() {}
}
