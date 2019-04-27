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
    private Integer user_id;

    @Column(length = 4095)
    private String comment_text;

    @Column()
    private Integer post_id;

    public Comment() {
    }

    public Comment(Integer user_id, String comment_text, Integer post_id) {
        this.user_id = user_id;
        this.comment_text = comment_text;
        this.post_id = post_id;
    }

    public Integer getComment_id() {
        return comment_id;
    }


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }
}
