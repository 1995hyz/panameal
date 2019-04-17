package Application.Controller;

import Application.model.User;
import Application.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{username}", method= RequestMethod.POST)
    public ResponseEntity getUserProfile(
        @PathVariable("username") String username, @RequestBody User user) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
