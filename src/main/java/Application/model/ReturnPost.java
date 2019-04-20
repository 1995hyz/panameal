package Application.model;

public class ReturnPost {
    private Post post;
    private String username;

    public ReturnPost() {}

    public ReturnPost(Post post, String username){
        this.post = post;
        this.username = username;
    }

    public void setPost(Post post){
        this.post = post;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Post getPost(){
        return this.post;
    }

    public String getUsername(){
        return this.username;
    }

}
