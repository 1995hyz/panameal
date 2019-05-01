package Application.model;

public class FollowingForm {
    private String email;
    private String usernameFollowing;

    public FollowingForm(String email, String usernameFollowing) {
        this.email = email;
        this.usernameFollowing = usernameFollowing;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsernameFollowing() {
        return usernameFollowing;
    }

    public void setUsernameFollowing(String usernameFollowing) {
        this.usernameFollowing = usernameFollowing;
    }
}
