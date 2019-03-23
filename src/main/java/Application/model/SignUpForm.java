package Application.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignUpForm {

    String email;
    String username;
    String passwordHash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String storeProfileImage(Integer userId, BufferedImage profilePic){
        String imagePath = "C:\\Panameal_Images\\" + Integer.toString(userId) + "\\Profile_Pic";
        File dirPath = new File(imagePath);
        if(!dirPath.exists()) {
            dirPath.mkdir();
        }
        File userImage = new File(imagePath + "\\Profile");
        try {
            ImageIO.write(profilePic, "jpg", userImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath;
    }

}
