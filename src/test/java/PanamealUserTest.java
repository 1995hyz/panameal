import Application.model.LoginForm;
import Application.model.Post;
import Application.model.User;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PanamealUserTest {

    @Test
    public void testUserMem(){
        String email = "tmp@test.com";
        String username = "test";
        String password = "1234567890";
        User tmp = new User(email, username, password);
        assertEquals(email,tmp.getEmail());
        assertEquals(username,tmp.getUsername());
        assertEquals(password,tmp.getPasswordHash());
        tmp = null;
    }

    @Test
    public void testLogin(){
        String email = "test@test.com";
        String passwordHash = "0123456789";
        LoginForm testLogin = new LoginForm(email,passwordHash);
        testLogin = null;
    }

    @Test
    public void testPostMem(){
        Integer user = 7;
        String content = "Test Post";
        Integer privacy_level = 2;
        Post testPost = new Post(user, content, privacy_level);
        assertEquals(user, testPost.getUser_id());
        assertEquals(content, testPost.getContent());
        assertEquals(privacy_level, testPost.getPrivacy_level());
        testPost = null;
    }
    /*public void addUser() {
        assert(PanamealUser.addUser("hao2@cooper.edu", "hao2", "121232342") == 1);
    }

    public void updateUserGeneral() {
    }

    public void updateUserCredential() {
    }

    public static void main(String[] args) {
        PanamealUserTest user = new PanamealUserTest();
        user.addUser();
    }*/

}
