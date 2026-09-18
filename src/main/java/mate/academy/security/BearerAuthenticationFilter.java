package mate.academy.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

public class BearerAuthenticationFilter extends HttpFilter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);

        // generate token String(username)
            // Util:raw token with secret String and then hash it with toHexString
        // send this token to client
            // Util: hashed raw token decode in jwt parts array and check if secret == our secret + validation about token expiration
        // receive this token from request header
            // Here extractToken(HttpServletRequest request) and then in doFilter()
            // Here UsernamePasswordAuthenttication = new
            // Here SecurityContextHolder
            // Check valid from Util
        // validate token and identify client
            // Util:extractUsername(String token)
    }
}
