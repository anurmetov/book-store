package mate.academy.security;

import java.nio.charset.StandardCharsets;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private final static String SECRET_CODE = "ArtemMateAcademy";
    private final static String SEPARATOR = ":";

    public String generateToken(String email) {
        String rawToken = email + SEPARATOR + System.currentTimeMillis() + SEPARATOR + SECRET_CODE;
        return toHexString(rawToken.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isTokenValid(String token) {
        String[] tokenParts = token.split(SEPARATOR);

        return tokenParts[2].equals(SECRET_CODE);
    }

    public String extractEmail(String token) {
        if (!isTokenValid(token)) {
            return null;
        }
        String rawToken = new String(fromHexString(token), StandardCharsets.UTF_8);
        return rawToken.split(SEPARATOR)[0];
    }

    private String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private byte[] fromHexString(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
