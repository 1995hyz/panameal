package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "follower")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer follower_id;

    @Column(name = "user_id")
    private Integer userId;

    @Column()
    private Integer followby;

    public Follower(Integer userId, Integer followby) {
        this.userId = userId;
        this.followby = followby;
    }

    public Integer getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Integer follower_id) {
        this.follower_id = follower_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowby() {
        return followby;
    }

    public void setFollowby(Integer followby) {
        this.followby = followby;
    }
}
