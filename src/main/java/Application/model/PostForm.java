package Application.model;

public class PostForm {
    String email;
    String post;
    int privacy_level;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getPrivacy_level() {
        return privacy_level;
    }

    public void setPrivacy_level(int privacy_level) {
        this.privacy_level = privacy_level;
    }
}
