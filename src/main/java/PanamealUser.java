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

    public ResultSet addUser() {
        PanamealSQLHandler myHandler = new PanamealSQLHandler();
        ResultSet response = myHandler.execSQL("INSERT INTO user (Email, User_Name, Password_Hash) VALUES (" +
                this.email + "," + this.username + "," + this.passwordHash + ")");
        if(response == null){
            return null;
        }
        return response;
    }

    public ResultSet updateUserGeneral() {
        PanamealSQLHandler myHandler = new PanamealSQLHandler();
        ResultSet response = myHandler.execSQL("UPDATE user SET");
        if(response == null){
            return null;
        }
        return response;
    }

    public ResultSet updateUserCredential() {
        return null;
    }

}
