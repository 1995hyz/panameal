package model;

import java.util.HashMap;

public class UserDB {
    HashMap<String, User> userDB = new HashMap<String, User>();

    public UserDB() {
        User user1 = new User();
        user1.setEmail("test@cooper.edu");
        user1.setUsername("test");
        user1.setPasswordHash("12345678");
        userDB.put(user1.getEmail(), user1);

        User user2 = new User();
        user2.setEmail("test2@cooper.edu");
        user2.setUsername("test2");
        user2.setPasswordHash("87654321");
        userDB.put(user2.getEmail(), user2);
    }

    public HashMap<String, User> getUserDB() {
        return userDB;
    }
}
