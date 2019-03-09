import java.sql.ResultSet;

public class PanamealUser {
    private String email, username, passwordHash;
    private String firstname, lastname, bio, emailSec, phone, imagePath;
    private int privacyLevel;
    private static PanamealSQLHandler myHandler = new PanamealSQLHandler();

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
        int response = myHandler.execUpdate("INSERT INTO User (Email, User_Name, Password_Hash) VALUES (\"" +
                this.email + "\",\"" + this.username + "\",\"" + this.passwordHash + "\");");
        return response;
    }

    public static int retrieveId(String email) {
        ResultSet response = myHandler.execQuery("SELECT User_Id FROM User WHERE Email=\"" + email + "\";");
        try {
            response.first();
            return response.getInt("User_Id");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Fail to retrieve Id with email");
            return 0;
        }
    }

    public static int dropUser(String email) {
        int userId = retrieveId(email);
        if(userId == 0) {
            return 0;
        }
        return myHandler.execUpdate("DELETE FROM User WHERE User_Id = " + userId);
    }

    public int updateUserGeneral() {
        PanamealSQLHandler myHandler = new PanamealSQLHandler();
         return myHandler.execUpdate("UPDATE user SET");
    }

    public ResultSet updateUserCredential() {
        return null;
    }

    public static ResultSet getUser(String Email){
        return null;
    }

}
