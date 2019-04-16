package Application.model;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 2047)
    private String bio;
    @Column(length = 63)
    private String email;
}
