package Application.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer post_id;

    @Column()
    private Integer user_id;
    @Column(length = 4095)
    private String content;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date post_time;
    @Column()
    private Integer privacy_level;

    public Post() {}

    public Post(Integer user, String content, Integer privacy_level) {
        this.user_id = user;
        this.content = content;
        this.privacy_level = privacy_level;
        this.post_time = new java.util.Date();
    }

    public Integer getPost_id() {
        return post_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public Integer getPrivacy_level() {
        return privacy_level;
    }

    public void setPrivacy_level(Integer privacy_level) {
        this.privacy_level = privacy_level;
    }
}
