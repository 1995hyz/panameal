package Application.model;

import java.util.ArrayList;

public class Profile {
    private UserProfile user;
    private ArrayList<ReturnPost> post;
    private Integer followFlag;

    public Profile(UserProfile user, ArrayList<ReturnPost> post, Integer followFlag){
        this.user = user;
        this.post = post;
        this.followFlag = followFlag;
    }

    public void setUser(UserProfile user){
        this.user = user;
    }

    public void setPost(ArrayList<ReturnPost> post){
        this.post = post;
    }

    public UserProfile getUser(){
        return user;
    }

    public ArrayList<ReturnPost> getPost(){
        return post;
    }

    public Integer getFollowFlag() {
        return followFlag;
    }

    public void setFollowFlag(Integer followFlag) {
        this.followFlag = followFlag;
    }
}
