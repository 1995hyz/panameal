import java.sql.ResultSet;

public class Temp {
    private static void addUser() {
        if(PanamealUser.addUser("hao2@cooper.edu", "hao2", "121232342") == 1) {
            System.out.println("Add!");
        }
        else {
            System.out.println("Fail!");
        }
    }

    private static void dropUser() {
        if(PanamealUser.dropUser("hao2@cooper.edu") == 1) {
            System.out.println("Drop!");
        }
        else {
            System.out.println("Fail!");
        }
    }

    private static void getUser() {
        ResultSet rs = PanamealUser.getUser("hao2@cooper.edu");
        if(rs == null) {
            System.out.println("Fail");
        }
        else {
            try {
                while (rs.next()) {
                    System.out.println(rs);
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                System.out.println("Fail");
            }
        }
    }

    private static void updateUserCredential() {
        if(PanamealUser.updateCredential("hao2@cooper.edu", "11111111111") == 1) {
            System.out.println("Changed");
        }
        else {
            System.out.println("Fail");
        }

    }

    public static void main(String[] args){
        //addUser();
        //dropUser();
        //getUser();
        updateUserCredential();
    }
}
