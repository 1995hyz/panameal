package Application.model;

public class PostForm {
    String email;
    String content;
    int privacy_level;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrivacy_level() {
        return privacy_level;
    }

    public void setPrivacy_level(int privacy_level) {
        this.privacy_level = privacy_level;
    }
}
