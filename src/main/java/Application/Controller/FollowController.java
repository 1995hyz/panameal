package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class FollowController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowingRepository followingRepository;

    @RequestMapping(value = "/following", method = RequestMethod.POST)
    public ResponseEntity<Optional> addFollowing(@RequestBody FollowingForm followingForm) {
        Optional<User> currUser = userRepository.findByEmail(followingForm.getEmail());
        Optional<User> followingUser = userRepository.findByUsername(followingForm.getUsernameFollowing());
        if(currUser.isEmpty() || followingUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }
        else {
            Following newFollowing = new Following(currUser.get().getId(), followingUser.get().getId());
            followingRepository.save(newFollowing);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
