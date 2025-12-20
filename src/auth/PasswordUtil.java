package auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public String encryptPassword(String password){
        try {
            MessageDigest mg = MessageDigest.getInstance("SHA-256");
            byte[] pw = mg.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for(byte b : pw){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
