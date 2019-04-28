package Application.model;

public class UserProfile {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String bio;
    private String emailSecond;
    private String phone;

    public UserProfile() {

    }

    public UserProfile(String firstname, String lastname, String username, String bio, String emailSecond, String phone, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.bio = bio;
        this.emailSecond = emailSecond;
        this.phone = phone;
        this.password = password;
    }

    public UserProfile(String firstname, String lastname, String username, String bio, String emailSecond, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.bio = bio;
        this.emailSecond = emailSecond;
        this.phone = phone;
    }

    public UserProfile(String firstname, String lastname, String username){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getUsername(){
        return this.username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
