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


}
