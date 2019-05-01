package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
            //Follower newFollower = new Follower(followingUser.get().getId(), currUser.get().getId());
            followingRepository.save(newFollowing);
            //followerRepository.save(newFollower);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/unfollowing", method = RequestMethod.POST)
    public ResponseEntity<Optional> deleteFollowing(@RequestBody FollowingForm followingForm) {
        Optional<User> currUser = userRepository.findByEmail(followingForm.getEmail());
        Optional<User> followingUser = userRepository.findByUsername(followingForm.getUsernameFollowing());
        if(currUser.isEmpty() || followingUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<Following> follow = followingRepository.findByUseridAndFollowto(currUser.get().getId(), followingUser.get().getId());
            if(follow.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            } else{
                System.out.println(currUser.get().getId());
                System.out.println(followingUser.get().getId());
                followingRepository.delete(follow.get());
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            //Following newFollowing = new Following(currUser.get().getId(), followingUser.get().getId());

        }
    }

    @RequestMapping(value = "/following_list")
    public ResponseEntity<ArrayList<String>> findFollowing(@RequestBody String username) {
        Optional<User> currUser = userRepository.findByUsername(username);
        if(currUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        else {
            ArrayList<String> followingList = new ArrayList<>();
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(currUser.get().getId());
            Iterable<Following> results = followingRepository.findAllByUserid(temp);
            results.forEach(result->{
                Optional<User> user = userRepository.findById(result.getFollowto());
                if(user.isPresent()) {
                    followingList.add(user.get().getUsername());
                }
            });
            return new ResponseEntity<>(followingList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/follower_list")
    public ResponseEntity<ArrayList<String>> findFollower(@RequestBody String username) {
        Optional<User> currUser = userRepository.findByUsername(username);
        if(currUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        else {
            ArrayList<String> followerList = new ArrayList<>();
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(currUser.get().getId());
            Iterable<Following> results = followingRepository.findAllByFollowto( temp );
            results.forEach(result->{
                Optional<User> user = userRepository.findById(result.getUserid());
                if(user.isPresent()) {
                    followerList.add(user.get().getUsername());
                }
            });
            return new ResponseEntity<>(followerList, HttpStatus.OK);
        }
    }
}
