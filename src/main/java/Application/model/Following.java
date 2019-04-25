package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "following")
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer following_id;

    @Column(name = "user_id")
    private Integer userId;

    @Column()
    private Integer followto;

    public Following(Integer userId, Integer followto) {
        this.userId = userId;
        this.followto = followto;
    }

    public Integer getFollowing_id() {
        return following_id;
    }

    public void setFollowing_id(Integer following_id) {
        this.following_id = following_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public Integer getFollowto() {
        return followto;
    }

    public void setFollowto(Integer followto) {
        this.followto = followto;
    }
}
