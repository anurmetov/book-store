package mate.academy.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class BearerAuthenticationFilter extends HttpFilter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {


        String token = extractToken((HttpServletRequest) request);
        UsernamePasswordAuthenticationFilter ua = new UsernamePasswordAuthenticationFilter(
                AuthenticationManager
        )


        chain.doFilter(request,response);

        // TODO: Generate token in TokenUtil generateToken(String email) Done
        // TODO: Send this token to client : DONE
        // receive this token from request header
            // Here extractToken(HttpServletRequest request) and then in doFilter()
            // Here UsernamePasswordAuthenttication = new
            // Here SecurityContextHolder
            // Check valid from Util
        // TODO: validate token and identify client DONE
            // Util:extractUsername(String token)
    }

    private String extractToken(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }
}
