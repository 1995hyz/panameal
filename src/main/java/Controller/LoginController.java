package Controller;

import model.LoginForm;
import model.UserDB;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.LoginService;

import model.User;
import java.util.HashMap;

@RestController

public class LoginController {

    @Autowired
    LoginService ls;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity <LoginForm> getLoginForm(@RequestBody LoginForm loginForm) {
        System.out.println(loginForm.getEmail());
        System.out.println(loginForm.getPasswordHash());
        UserDB sampleUserDB = new UserDB();
        HashMap<String, User> sampleUsers = sampleUserDB.getUserDB();
        if(sampleUsers.containsKey(loginForm.getEmail())) {
            if(sampleUsers.get(loginForm.getEmail()).getPasswordHash().equals(loginForm.getPasswordHash())) {
                return new ResponseEntity <LoginForm>(loginForm, HttpStatus.OK);
            }
            else {
                return new ResponseEntity <LoginForm> (loginForm, HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity <LoginForm> (loginForm, HttpStatus.UNAUTHORIZED);
        }
        //return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping("/signup")
    public ResponseEntity signUpUser(@RequestBody User user) {
        return ResponseEntity.ok(HttpStatus.OK);
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
