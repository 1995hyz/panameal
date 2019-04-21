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
    public ResponseEntity addFollowing(@RequestBody FollowingForm followingForm) {
        Optional<User> currUser = userRepository.findByEmail(followingForm.getUser_email());
        if(currUser.isEmpty()) {
            return new ResponseEntity (null, HttpStatus.UNAUTHORIZED);
        }
        else{
            Following following = new Following(currUser.get().getId());
            followingRepository.save(following);
            return new ResponseEntity (null, HttpStatus.OK);
        }
    }
}
