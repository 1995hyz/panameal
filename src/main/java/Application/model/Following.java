package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "following")
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer following_id;

    @Column()
    private Integer user_id;

    @Column()
    private Integer followto;

    public Following(Integer user_id, Integer followto) {
        this.user_id = user_id;
        this.followto = followto;
    }

    public Integer getFollowing_id() {
        return following_id;
    }

    public void setFollowing_id(Integer following_id) {
        this.following_id = following_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFollowto() {
        return followto;
    }

    public void setFollowto(Integer followto) {
        this.followto = followto;
    }
}
