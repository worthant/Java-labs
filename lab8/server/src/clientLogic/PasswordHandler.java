package clientLogic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler {

    public static String hashPassword(char[] password, String salt) {
        String input = String.valueOf(password) + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD2 hashing algorithm not available.", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
