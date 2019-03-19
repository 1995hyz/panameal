package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.LoginService;

import model.User;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService ls;
}
