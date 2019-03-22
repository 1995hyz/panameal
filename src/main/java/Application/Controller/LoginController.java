package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Application.service.LoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController

public class LoginController {

    @Autowired
    LoginService ls;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Optional> getLoginForm(@RequestBody LoginForm loginForm) {
        Optional<User> currUser = userRepository.findByEmail(loginForm.getEmail());
        if(currUser.isEmpty()) {
            return new ResponseEntity <>(null, HttpStatus.OK);
        }
        else {
            if(currUser.get().getPasswordHash().equals(loginForm.getPasswordHash())) {
                return new ResponseEntity<>(currUser, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
        /*
        To test, use the following command:
            curl localhost:8080/login -i -H "Accept: application/json" -H "Content-Type:application/json"
            -X POST --data "{"""email""":"""test@cooper.edu""", """username""":"""hello"""}"
         */
    }

    @RequestMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody SignUpForm signUpForm) {
        Optional<User> currUser = userRepository.findByEmail(signUpForm.getEmail());
        if(currUser.isEmpty()) {
            User newUser = new User(signUpForm.getEmail(), signUpForm.getUsername(), signUpForm.getPasswordHash());
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @RequestMapping("/")
    public ResponseEntity<String> test() {
        return new ResponseEntity<> ("Hello World!", HttpStatus.OK);
    }

    /* To test, use the following command:
        curl localhost:8080/login
        -i -H "Accept: application/json" -H "Content-Type:application/json"
        -X POST --data '{"email":"test@cooper.edu", "passwordHash":"12345678"}'
       For windows cmd:
        curl localhost:8080/login
        -i -H "Accept: application/json" -H "Content-Type:application/json"
        -X POST --data "{"""email""":"""test@cooper.edu""", """passwordHash""":"""12345678"""}"
     */

}
