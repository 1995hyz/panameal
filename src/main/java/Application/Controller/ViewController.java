package Application.Controller;

import Application.model.Post;
import Application.model.PostRepository;
import Application.model.PostRequestForm;
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

    @RequestMapping("/view_post")
    public ResponseEntity<ArrayList<Post>> viewPost(@RequestBody PostRequestForm postRequest) {
        /*try {
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM post WHERE user_id != " + postRequest.getUser_id() +
                " LIMIT " + postRequest.getAmount() + " ;";
        Session session = factory.openSession();
        Query q = session.createQuery(query);
        List result = q.list();
        for(Iterator iterator = result.iterator(); iterator.hasNext();) {
            iterator.next();
            Post post = (Post)iterator.next();
            System.out.println((post.getContent()));
        }*/
        ArrayList<Post> postList= new ArrayList<Post>();
        Iterable<Post> results = postRepository.findAll();
        for(Iterator iterator = results.iterator(); iterator.hasNext();) {
            Post post = (Post) iterator.next();
            postList.add(post);
            //System.out.println(post.getContent());
        }
        Collections.reverse(postList);
        List postL = postList.subList(0, postRequest.getAmount());
        ArrayList<Post> finalPost = new ArrayList<Post>(postL);
        return new ResponseEntity<>(finalPost, HttpStatus.OK);
    }
}
