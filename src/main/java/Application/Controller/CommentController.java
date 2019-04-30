package Application.Controller;

import Application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @RequestMapping("/create/comment")
    public ResponseEntity <Comment> createComment(@RequestBody CommentForm commentForm){
        Optional<User> currUser = userRepository.findByEmail(commentForm.getEmail());
        if(currUser.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else{
            Comment newComment = new Comment(currUser.get().getId(), commentForm.getCommentText(), commentForm.getPost_Id());
            commentRepository.save(newComment);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

    @RequestMapping("/view_comment")
    public ResponseEntity <ArrayList<Comment>> viewComment(@RequestBody CommentRequestForm commentRequestForm){
        Optional<Post> currPost = postRepository.findById(commentRequestForm.getPost_id());
        if(currPost.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else{
            ArrayList<Comment> comments = new ArrayList<>();
            Iterable<Comment> results = commentRepository.findAllByPostId(commentRequestForm.getPost_id());
            for(Iterator iterator = results.iterator(); iterator.hasNext();) {
                Comment comment = (Comment) iterator.next();
                comments.add(comment);
            }
            List<Comment> commentList;
            if(commentRequestForm.getAmount() > comments.size()) {
                commentList = comments;
            }
            else {
                commentList = comments.subList(0, commentRequestForm.getAmount());
            }
            ArrayList<Comment> finalComment = new ArrayList<Comment>(commentList);
            return new ResponseEntity<>(finalComment, HttpStatus.OK);
        }
    }

}
