package Application.model;

public class CommentForm {

    private String email;
    private Integer post_Id;
    private String comment_text;

    public CommentForm(String email, Integer post_Id, String commentText) {
        this.email = email;
        this.post_Id = post_Id;
        this.comment_text = commentText;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPost_Id() {
        return post_Id;
    }

    public void setPost_Id(Integer post_Id) {
        this.post_Id = post_Id;
    }

    public String getCommentText() {
        return comment_text;
    }

    public void setCommentText(String commentText) {
        this.comment_text = commentText;
    }
}
