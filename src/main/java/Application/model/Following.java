package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "following")
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer following_id;

    @Column()//name = "userId")
    private Integer userid;

    @Column()
    private Integer followto;

    public Following() {}

    public Following(Integer userId, Integer followto) {
        this.userid = userId;
        this.followto = followto;
    }

    public Integer getFollowing_id() {
        return following_id;
    }

    public void setFollowing_id(Integer following_id) {
        this.following_id = following_id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userId) {
        this.userid = userId;
    }

    public Integer getFollowto() {
        return followto;
    }

    public void setFollowto(Integer followto) {
        this.followto = followto;
    }
}
