package Application.model;

import java.util.ArrayList;

public class Profile {
    private UserProfile user;
    private ArrayList<Post> post;
    private Integer followFlag;

    public Profile(UserProfile user, ArrayList<Post> post, Integer followFlag){
        this.user = user;
        this.post = post;
        this.followFlag = followFlag;
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

    public Integer getFollowFlag() {
        return followFlag;
    }

    public void setFollowFlag(Integer followFlag) {
        this.followFlag = followFlag;
    }
}
