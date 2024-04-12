package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// This class is a filter that intercepts all requests and extracts the JWT token from the Authorization header
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = this.getTokenJWT(request);
        System.out.println("Token JWT: " + tokenJWT);
    }

    private String getTokenJWT(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) return null;

        return authHeader.replace ("Bearer ", ""); // remove the "Bearer " prefix
    }
}
