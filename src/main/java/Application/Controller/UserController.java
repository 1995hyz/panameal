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
        int followFlag = 0;
        if(viewUser.isEmpty()) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }

        if(currUser.isEmpty()){
            followFlag = -1;
        } else{
            Optional<Following> follow = followingRepository.findByUserIdAndAndFollowto(currUser.get().getId(), viewUser.get().getId());
            if(!follow.isEmpty()){
                followFlag = 1;
            }
        }

        /*Iterable<Following> resultsFollow = followingRepository.findAllByUserId( new ArrayList<> (currUser.get().getId()));
        for(Iterator iterator=resultsFollow.iterator(); iterator.hasNext();) {
            Following following = (Following) iterator.next();
            if (following.getFollowto().equals(currUser.get().getId())) {
                followFlag = 1;
            }
        }*/

        UserProfile userProfile = new UserProfile(viewUser.get().getFirstname(), viewUser.get().getLastname(),
                viewUser.get().getUsername(), viewUser.get().getBio(), viewUser.get().getEmailSecond(),
                viewUser.get().getPhone());
        ArrayList<ReturnPost> postList= new ArrayList<>();
        Iterable<Post> results = postRepository.findAll();

        for(Iterator iter = results.iterator(); iter.hasNext();) {
            Post post = (Post) iter.next();
            if(post.getUser_id().equals(viewUser.get().getId())){
                System.out.println(post.getUser_id());
                ReturnPost rp = new ReturnPost(post, followPage.getUsername());
                postList.add(rp);
            }
        }
        Collections.reverse(postList);
        ArrayList<ReturnPost> finalPost = new ArrayList<>(postList);
        Profile profile = new Profile(userProfile,finalPost, followFlag);

        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

}
