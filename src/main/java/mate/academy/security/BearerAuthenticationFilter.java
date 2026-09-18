package mate.academy.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class BearerAuthenticationFilter extends HttpFilter {

    private final TokenUtil tokenUtil;

    public static final String AUTHORIZATION_SCHEMA_BEARER = "Bearer";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String token = extractToken((HttpServletRequest) request);

        if (token != null && tokenUtil.isTokenValid(token)) {

            UsernamePasswordAuthenticationToken ua =
                    new UsernamePasswordAuthenticationToken(
                            tokenUtil.extractEmail(token),
                            null
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(ua);
        }

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null) {
            return null;
        }

        header = header.trim();

        if (!StringUtils.startsWithIgnoreCase(
                header,
                AUTHORIZATION_SCHEMA_BEARER)) {
            return null;
        }

        if (header.equalsIgnoreCase(
                AUTHORIZATION_SCHEMA_BEARER)) {
            throw new BadCredentialsException(
                    "Empty bearer authentication token");
        }

        return header.substring(7);
    }
}