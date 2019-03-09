
public class PanamealUserTest {

    public void addUser() {
        PanamealUser my_user = new PanamealUser("hao2@cooper.edu", "hao2", "1223324343");
        assert(my_user.addUser() == 1);
    }

    public void updateUserGeneral() {
    }

    public void updateUserCredential() {
    }

    public static void main(String[] args) {
        PanamealUserTest user = new PanamealUserTest();
        user.addUser();
    }

}
