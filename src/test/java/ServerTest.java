public class ServerTest {
    public static void main(){
        String email = "hao2@cooper.edu";
        String usermame = "hao2";
        String password_hash = "1234567";
        PanamealUser my_user = new PanamealUser(email, usermame, password_hash);
        my_user.addUser();
    }

}
