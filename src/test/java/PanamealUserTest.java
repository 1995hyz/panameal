import Application.Controller.UserController;
import Application.model.*;
import Application.service.LoginService;
import Application.service.UserService;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void testSignUp(){
        String email = "tmp@test.com";
        String username = "test";
        String password = "1234567890";
        SignUpForm testSignUpForm = new SignUpForm();
        testSignUpForm.setEmail(email);
        testSignUpForm.setUsername(username);
        testSignUpForm.setPasswordHash(password);
        User testUser = new User(testSignUpForm.getEmail(), testSignUpForm.getUsername(), testSignUpForm.getPasswordHash());
        assertEquals(email,testUser.getEmail());
        assertEquals(username,testUser.getUsername());
        assertEquals(password,testUser.getPasswordHash());
        //ResponseEntity<> tmp = ResponseEntity<User> signUpUser(testSignUpForm);
    }

    @Test
    public void testSignUpRoute(){
        String email = "tmp@test.com";
        String username = "test";
        String password = "1234567890";
        SignUpForm testSignUpForm = new SignUpForm();
        testSignUpForm.setEmail(email);
        testSignUpForm.setUsername(username);
        testSignUpForm.setPasswordHash(password);
        User testUser = new User(testSignUpForm.getEmail(), testSignUpForm.getUsername(), testSignUpForm.getPasswordHash());
        assertEquals(email,testUser.getEmail());
        assertEquals(username,testUser.getUsername());
        assertEquals(password,testUser.getPasswordHash());
        //ResponseEntity<> tmp = ResponseEntity<User> signUpUser(testSignUpForm);
    }


    @Test
    public void testLoginRoute(){
        /*UserRepository mockRepo = (UserRepository) mock(LoginService.class);
        String email = "test@test.com";
        String passwordHash = "0123456789";
        String username = "UnitTest";
        User usr1 = new User(email,username,passwordHash);
        when(mockRepo.findAll()).thenReturn(Arrays.asList(usr1));
        LoginService service = new LoginService(mockRepo);
        LoginForm testLogin = new LoginForm(email,passwordHash);
        //Optional<User> currUser = mockRepo.findByEmail(testLogin.getEmail());
        //assertEquals(email, currUser.get().getEmail());
        testLogin = null;*/
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
