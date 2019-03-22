package Application.Controller;

import Application.model.LoginForm;
import Application.model.UserDB;
import Application.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Application.service.LoginService;

import Application.model.User;
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
        /*System.out.println(loginForm.getEmail());
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
        }*/
        //return ResponseEntity.ok(HttpStatus.OK);
        //System.out.println(loginForm.getEmail());
        Optional<User> curr_user = userRepository.findByEmail(loginForm.getEmail());
        return new ResponseEntity <>(curr_user, HttpStatus.OK);
        /*
        To test, use the following command:
            curl localhost:8080/login -i -H "Accept: application/json" -H "Content-Type:application/json"
            -X POST --data "{"""email""":"""test@cooper.edu""", """username""":"""hello"""}"
         */
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
