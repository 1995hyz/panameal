package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "follower")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer follower_id;

    @Column()
    private Integer user_id;

    @Column
    private Integer followby;

    public Follower(Integer user_id, Integer followby) {
        this.user_id = user_id;
        this.followby = followby;
    }

    public Integer getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Integer follower_id) {
        this.follower_id = follower_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFollowby() {
        return followby;
    }

    public void setFollowby(Integer followby) {
        this.followby = followby;
    }
}
