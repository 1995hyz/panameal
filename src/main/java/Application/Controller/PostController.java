package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/create/post")
    public ResponseEntity<Post> createPost(@RequestBody PostForm postForm) {
        Optional<User> currUser = userRepository.findByEmail(postForm.getEmail());
        if(currUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        else {
            int privacyLevel = postForm.getPrivacy_level();
            if( privacyLevel != 1 && privacyLevel != 2 && privacyLevel != 3){
                privacyLevel = 1;
            }
            Post newPost = new Post(currUser.get().getId(), postForm.getContent(), privacyLevel);
            postRepository.save(newPost);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
