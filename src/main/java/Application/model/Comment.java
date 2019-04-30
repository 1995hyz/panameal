package Application.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comment_id;

    @Column()
    private Integer userId;

    @Column(length = 4095)
    private String commentText;

    @Column()
    private Integer postId;

    public Comment() {
    }

    public Comment(Integer userId, String commentText, Integer postId) {
        this.userId = userId;
        this.commentText = commentText;
        this.postId = postId;
    }

    public Integer getComment_id() {
        return comment_id;
    }


    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }

    public String getcommentText() {
        return commentText;
    }

    public void setcommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getpostId() {
        return postId;
    }

    public void setpostId(Integer postId) {
        this.postId = postId;
    }
}
