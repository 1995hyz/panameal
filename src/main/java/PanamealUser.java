import java.sql.ResultSet;

public class PanamealUser {
    private String email, username, passwordHash;
    private String firstname, lastname, bio, emailSec, phone, imagePath;
    private int privacyLevel;

    PanamealUser(String email, String username, String passwordHash) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.privacyLevel = 3;
    }

    PanamealUser(String email, String username, String passwordHash,
                 String firstname, String lastname, String bio,
                 String emailSec, String phone, String imagePath) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.emailSec = emailSec;
        this.phone = phone;
        this.imagePath = imagePath;
    }

    public int addUser() {
        PanamealSQLHandler myHandler = new PanamealSQLHandler();
        int response = myHandler.execUpdate("INSERT INTO User (Email, User_Name, Password_Hash) VALUES (\"" +
                this.email + "\",\"" + this.username + "\",\"" + this.passwordHash + "\");");
        return response;
    }

    public static int dropUser(String email) {
        PanamealSQLHandler myHandler = new PanamealSQLHandler();
        int response = myHandler.execUpdate("");
        return response;
    }

    public int updateUserGeneral() {
        PanamealSQLHandler myHandler = new PanamealSQLHandler();
         return myHandler.execUpdate("UPDATE user SET");
    }

    public ResultSet updateUserCredential() {
        return null;
    }

}
