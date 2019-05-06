import Application.model.*;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class PanamealUserTest {

    @Mock
    UserRepository userMock;

    @Mock
    PostRepository postMock;

    @Mock
    FollowingRepository followMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testUserMem(){
        String email = "tmp@test.com";
        String username = "test";
        String password = "1234567890";
        User tmp = new User(email, username, password);
        assertEquals(email,tmp.getEmail());
        assertEquals(username,tmp.getUsername());
        tmp = null;
    }

    @Test
    public void testLoginMem(){
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
        Optional<User> currUser = userMock.findByEmail(testSignUpForm.getEmail());
        if(currUser.isEmpty()) {
            User newUser = new User(testSignUpForm.getEmail(), testSignUpForm.getUsername(), testSignUpForm.getPasswordHash(),
                    testSignUpForm.getFirstname(), testSignUpForm.getLastname(), "", "", testSignUpForm.getPhoneNumber(), 1, "");
            when(userMock.save(any(User.class))).thenAnswer(new Answer<User>() {
                @Override
                public User answer(InvocationOnMock invocation) throws Throwable {
                    Object[] arguments = invocation.getArguments();
                    if (arguments != null && arguments.length > 0 && arguments[0] != null){

                        User user = (User) arguments[0];
                        user.setId(1);

                        return user;
                    }
                    return null;
                }
            });
            assertEquals(userMock.save(newUser), newUser);
        }

    }

    @Test
    public void testLoginRoute(){
        String email = "tmp@test.com";
        String username = "test";
        String password = "1234567890";
        User newUser = new User(email, username, password,
                "", "", "", "", "", 1, "");
        LoginForm LoginForm = new LoginForm();
        LoginForm.setEmail(email);
        LoginForm.setPasswordHash(password);
        when(userMock.findByEmail(email)).thenReturn(null);
        assertEquals(userMock.findByEmail(LoginForm.getEmail()), null);
    }

    @Test
    public void testPostRoute() {
        Integer userId = 1;
        String content = "This is a test.";
        Integer privacyLevel = 1;
        Post newPost = new Post(userId, content, privacyLevel);
        when(postMock.findAll()).thenReturn(null);
        assertEquals(postMock.findAll(), null);
    }

    @Test
    public void testFollowingRoute() {
        Integer userId = 1;
        Integer followto = 2;
        Following newFollowing = new Following(userId, followto);
        when(followMock.findByUserid(1)).thenReturn(null);
        assertEquals(followMock.findByUserid(1), null);
    }

    @Test
    public void testFollowing() {
        Integer userId = 1;
        Integer followto = 2;
        Following newFollowing = new Following(userId, followto);
        Optional<Following> currFollowing = followMock.findByUserid(userId);
        if(currFollowing.isEmpty()) {
            when(followMock.save(any(Following.class))).thenAnswer(new Answer<Following>() {
                @Override
                public Following answer(InvocationOnMock invocation) throws Throwable {
                    Object[] arguments = invocation.getArguments();
                    if (arguments != null && arguments.length > 0 && arguments[0] != null){

                        Following newFollowing = (Following) arguments[0];
                        newFollowing.setFollowing_id(1);
                        return newFollowing;
                    }
                    return null;
                }
            });
            assertEquals(followMock.save(newFollowing), newFollowing);
        }
    }
}
