package Application.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 63)
    private String email;
    @Column(length = 63)
    private String username;
    @Column(length = 63)
    private String passwordHash;
    @Column(length = 31)
    private String firstname;
    @Column(length = 31)
    private String lastname;
    @Column(length = 2017)
    private String bio;
    @Column(length = 63)
    private String emailSecond;
    @Column(length = 15)
    private String phone;
    private Integer privacyLevel;
    @Column(length = 255)
    private String imagePath;

    public User() {

    }

    public User(String email, String username, String passwordHash) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstname = "";
        this.lastname = "";
        this.bio = "";
        this.emailSecond = "";
        this.phone = "";
        this.privacyLevel = 3;
        this.imagePath = "";
    }

    public User(String email, String username, String passwordHash, String firstname, String lastname, String bio, String emailSecond, String phone, int privacyLevel, String imagePath) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.emailSecond = emailSecond;
        this.phone = phone;
        this.privacyLevel = privacyLevel;
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmailSecond() {
        return emailSecond;
    }

    public void setEmailSecond(String emailSecond) {
        this.emailSecond = emailSecond;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(Integer privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
