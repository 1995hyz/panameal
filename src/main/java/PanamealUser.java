import java.sql.ResultSet;

public class PanamealUser {
    private static PanamealSQLHandler myHandler = new PanamealSQLHandler();

    static int addUser(String email, String username, String passwordHash) {
        return myHandler.execUpdate("INSERT INTO User (Email, User_Name, Password_Hash) VALUES (\"" +
                email + "\",\"" + username + "\",\"" + passwordHash + "\");");
    }

    static int retrieveId(String email) {
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

    static int dropUser(String email) {
        return myHandler.execUpdate("DELETE FROM User WHERE Email = \"" + email + "\"");
    }

    static int updateUsername(String email, String username) {
        return myHandler.execUpdate("UPDATE User SET User_Name = \"" + username + "\"" +
                "WHERE Email = \"" + email + "\"");
    }

    static int updateBio(String email, String bio) {
        return myHandler.execUpdate("UPDATE User SET Bio = \"" + bio + "\"" +
                "WHERE Email = \"" + email + "\"");
    }

    static int updateCredential(String email, String passwordHash) {
        return myHandler.execUpdate("UPDATE User SET Password_Hash = \"" + passwordHash + "\"" +
                "WHERE Email = \"" + email + "\"");
    }

    static ResultSet getUser(String email) {
        System.out.println("SELECT * FROM User WHERE Email = \"" + email + "\";");
        return myHandler.execQuery("SELECT * FROM User WHERE Email = \"" + email + "\";");
    }
}
