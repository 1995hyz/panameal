import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PanamealSQLHandler {
    private String dbUrl;
    private String user;
    private String password;

    PanamealSQLHandler() {
        dbUrl = "jdbc:mysql://panameal.cjqisouazkzz.us-east-1.rds.amazonaws.com:3306/panameal";
        user = "deertianheng";
        password = "SpeedWagon95$";
    }

    PanamealSQLHandler(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    private Connection establishConnection() {
        try {
            Connection myConn = DriverManager.getConnection(this.dbUrl, this.user, this.password);
            return myConn;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }

    public ResultSet execSQL(String SQLCommand) {
        Connection myDB = establishConnection();
        try {
            Statement myStatement = myDB.createStatement();
            ResultSet myRe = myStatement.executeQuery(SQLCommand);
            return myRe;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Fail to create/exec a SQL statement.");
            return null;
        }
    }
}
