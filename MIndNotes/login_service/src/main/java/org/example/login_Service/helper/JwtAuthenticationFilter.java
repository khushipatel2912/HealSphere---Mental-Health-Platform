//package org.example.login_Service.helper;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
    //    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            String email = jwtUtil.extractEmail(token);
//
//            if (email != null && jwtUtil.isTokenValid(token)) {
//                // Add authentication context logic here
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
package org.example.login_Service.helper; // Ensure this matches your package structure

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger; // Use SLF4J for logging
import org.slf4j.LoggerFactory; // Use SLF4J for logging
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // Import this
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Use Lombok for constructor injection
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class); // Setup logger

    private final JwtUtil jwtUtil;
    // Inject the UserDetailsService (likely implemented by your UserService)
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // If Authorization header is missing or doesn't start with "Bearer ", pass request down the chain
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token (remove "Bearer ")
        jwt = authHeader.substring(7);

        try {
            // Extract email (or subject) from the token
            userEmail = jwtUtil.extractEmail(jwt);

            // If email is present and user is not already authenticated in the current security context
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Load user details from the database using the email from the token
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // Validate the token using the method signature from your JwtUtil
                // *** THIS IS THE CORRECTED LINE ***
                if (jwtUtil.isTokenValid(jwt)) {
                    // If token is valid, create an authentication token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,          // Principal: The user details object
                            null,                 // Credentials: Null for JWT authentication
                            userDetails.getAuthorities() // Authorities: User's roles/permissions
                    );
                    // Set additional details for the authentication token (e.g., IP address)
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    // Update the SecurityContextHolder with the new authentication object
                    // This marks the current user as authenticated for the duration of the request
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.debug("Successfully authenticated user '{}' via JWT.", userEmail); // Added debug log
                } else {
                    log.warn("JWT token is invalid according to JwtUtil for user '{}'.", userEmail); // Updated log message
                }
            }
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            // Optionally: set a specific response status or header for expired tokens
            // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // response.getWriter().write("JWT Token Expired");
            // return; // Exit filter chain early if needed
        } catch (SignatureException | MalformedJwtException e) {
            log.warn("JWT token signature or format is invalid: {}", e.getMessage());
        } catch (UsernameNotFoundException e) {
            log.warn("User details not found for email extracted from JWT: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
        }

        // Continue the filter chain regardless of authentication success/failure
        // (unless you explicitly stopped it above, e.g., for expired tokens)
        filterChain.doFilter(request, response);
    }
}