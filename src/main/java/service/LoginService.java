package service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    String email;
    String passwordHash;
    public LoginService(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

}
