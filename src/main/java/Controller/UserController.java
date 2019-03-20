package Controller;

import model.User;
import model.UserDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {

    @RequestMapping(value="/{username}", method= RequestMethod.POST)
    public ResponseEntity getUserProfile(
        @PathVariable("username") String username, @RequestBody User user) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
