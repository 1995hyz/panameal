package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
public class FollowController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowingRepository followingRepository;
    @Autowired
    private FollowerRepository followerRepository;

    @RequestMapping(value = "/following", method = RequestMethod.POST)
    public ResponseEntity<Optional> addFollowing(@RequestBody FollowingForm followingForm) {
        Optional<User> currUser = userRepository.findByEmail(followingForm.getEmail());
        Optional<User> followingUser = userRepository.findByUsername(followingForm.getUsernameFollowing());
        if(currUser.isEmpty() || followingUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }
        else {
            Following newFollowing = new Following(currUser.get().getId(), followingUser.get().getId());
            Follower newFollower = new Follower(followingUser.get().getId(), currUser.get().getId());
            followingRepository.save(newFollowing);
            followerRepository.save(newFollower);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/following_list")
    public ResponseEntity<ArrayList<String>> findFollowing(@RequestBody String email) {
        Optional<User> currUser = userRepository.findByEmail(email);
        if(currUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            ArrayList<String> followingList = new ArrayList<>();
            Iterable<Following> results = followingRepository.findAllByUserId( new ArrayList<> (currUser.get().getId()));
            System.out.println(currUser.get().getId());
            results.forEach(result->{
                Optional<User> user = userRepository.findById(result.getFollowto());
                if(user.isPresent()) {
                    followingList.add(user.get().getUsername());
                }
            });
            return new ResponseEntity<>(followingList, HttpStatus.OK);
        }
    }
}
