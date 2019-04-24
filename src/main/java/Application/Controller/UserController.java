package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value="/user", method= RequestMethod.POST)
    public ResponseEntity<Profile> getUserProfile(@RequestBody String username) {
        Optional<User> currUser = userRepository.findByUsername(username);
        if(currUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }
        UserProfile userProfile = new UserProfile(currUser.get().getFirstname(), currUser.get().getLastname(), currUser.get().getUsername());
        ArrayList<Post> postList= new ArrayList<>();
        Iterable<Post> results = postRepository.findAll();

        for(Iterator iter = results.iterator(); iter.hasNext();) {
            Post post = (Post) iter.next();
            if(post.getUser_id() == currUser.get().getId()){
                postList.add(post);
            }
        }
        Collections.reverse(postList);
        ArrayList<Post> finalPost = new ArrayList<>(postList);
        Profile profile = new Profile(userProfile,finalPost);

        return new ResponseEntity<>(profile,HttpStatus.OK);
    }
}
