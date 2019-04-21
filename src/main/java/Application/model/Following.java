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

    public Following(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFollowing_id() {
        return following_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
