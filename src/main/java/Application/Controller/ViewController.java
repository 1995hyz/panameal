package Application.Controller;

import Application.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class ViewController {

    //private static SessionFactory factory;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/view_post")
    public ResponseEntity<ArrayList<ReturnPost>> viewPost(@RequestBody PostRequestForm postRequest) {

        ArrayList<ReturnPost> postList= new ArrayList<>();
        Iterable<Post> results = postRepository.findAll();
        for(Iterator iterator = results.iterator(); iterator.hasNext();) {
            Post post = (Post) iterator.next();
            ReturnPost rp = new ReturnPost(post, userRepository.findById(post.getUser_id()).get().getUsername());
            postList.add(rp);
        }
        Collections.reverse(postList);
        List postL;
        if(postRequest.getAmount() > postList.size()) {
            postL = postList;
        }
        else {
            postL = postList.subList(0, postRequest.getAmount());
        }
        ArrayList<ReturnPost> finalPost = new ArrayList<ReturnPost>(postL);
        return new ResponseEntity<>(finalPost, HttpStatus.OK);
    }
    @RequestMapping("/view_profile")
    public ResponseEntity<User> viewUser(@RequestBody String email) {
        Optional<User> currUser = userRepository.findByEmail(email);
        if(currUser.isEmpty()) {
            return new ResponseEntity <>(null, HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity<>(currUser.get(), HttpStatus.OK);
        }
    }

    @RequestMapping("/update/user")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile){
        Optional<User> currUser = userRepository.findByUsername(userProfile.getUsername());
        if(currUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        } else {
            if(currUser.get().checkPassword(userProfile.getPassword(), currUser.get().getPasswordHash())) {
                User updateUser = currUser.get();
                updateUser.setFirstname(userProfile.getFirstname());
                updateUser.setLastname(userProfile.getLastname());
                updateUser.setBio(userProfile.getBio());
                updateUser.setEmailSecond(userProfile.getEmailSecond());
                updateUser.setPhone(userProfile.getPhone());
                userRepository.save(updateUser);
                userProfile.setPassword("");
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
