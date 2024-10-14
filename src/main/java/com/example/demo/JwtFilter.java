package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("jwtFilter")
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    
    public JwtFilter() {
		this.jwtUtil = new JwtUtil();
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
                                    jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {

        // Extract token from cookies
        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // Validate the token
        if (token != null && jwtUtil.isTokenValid(token)) {
            // Proceed with the filter chain if the token is valid
            filterChain.doFilter(request, response);
        } else {
            // If token is invalid or not present, return Unauthorized status
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            response.getWriter().flush();
        }
    }
}
