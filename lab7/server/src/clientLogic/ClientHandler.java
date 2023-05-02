package clientLogic;

import collectionStorageManager.PostgreSQLManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClientHandler {
    private final String name;
    private final char[] passwd;
    private static long userId;

    public ClientHandler(String name, char[] passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public boolean regUser() {
        PostgreSQLManager manager = new PostgreSQLManager();
        long id = manager.regUser(name, passwd);
        if (id > 0) {
            userId = id;
            return true;
        }
        return false;
    }

    public boolean authUser() {
        PostgreSQLManager manager = new PostgreSQLManager();
        long id = manager.authUser(name, passwd);
        if (id > 0) {
            userId = id;
            return true;
        }
        return false;
    }

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

    public static long getUserId() {
        return userId;
    }
}
