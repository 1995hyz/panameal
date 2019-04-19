package Application.model;

import java.util.ArrayList;

public class Profile {
    private UserProfile user;
    private ArrayList<Post> post;

    public Profile(UserProfile user, ArrayList<Post> post){
        this.user = user;
        this.post = post;
    }

    public void setUser(UserProfile user){
        this.user = user;
    }

    public void setPost(ArrayList<Post> post){
        this.post = post;
    }

    public UserProfile getUser(){
        return user;
    }

    public ArrayList<Post> getPost(){
        return post;
    }
}
