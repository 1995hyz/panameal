package Application.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer post_id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user_id;
    @Column(length = 4095)
    private String content;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date post_time;
    @Column()
    private Integer privacy_level;

    public Post() {}

    public Post(User user, String content, Integer privacy_level) {
        this.user_id = user;
        this.content = content;
        this.privacy_level = privacy_level;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public User getUser_id() {
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
