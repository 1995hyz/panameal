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
    @Autowired
    private FollowingRepository followingRepository;

    @RequestMapping(value="/user", method= RequestMethod.POST)
    public ResponseEntity<Profile> getUserProfile(@RequestBody FollowPage followPage) {
        Optional<User> currUser = userRepository.findByEmail(followPage.getEmail());
        Optional<User> viewUser = userRepository.findByUsername(followPage.getUsername());
        if(currUser.isEmpty() || viewUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }

        int followFlag = 0;
        Iterable<Following> resultsFollow = followingRepository.findAllByUserId( new ArrayList<> (currUser.get().getId()));
        for(Iterator iterator=resultsFollow.iterator(); iterator.hasNext();) {
            Following following = (Following) iterator.next();
            if (following.getFollowto().equals(currUser.get().getId())) {
                followFlag = 1;
            }
        }

        UserProfile userProfile = new UserProfile(viewUser.get().getFirstname(), viewUser.get().getLastname(), viewUser.get().getUsername());
        ArrayList<Post> postList= new ArrayList<>();
        Iterable<Post> results = postRepository.findAll();

        for(Iterator iter = results.iterator(); iter.hasNext();) {
            Post post = (Post) iter.next();
            if(post.getUser_id().equals(viewUser.get().getId())){
                postList.add(post);
            }
        }
        Collections.reverse(postList);
        ArrayList<Post> finalPost = new ArrayList<>(postList);
        Profile profile = new Profile(userProfile,finalPost, followFlag);

        return new ResponseEntity<>(profile,HttpStatus.OK);
    }
}
