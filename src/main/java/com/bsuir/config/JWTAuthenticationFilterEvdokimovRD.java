package com.bsuir.config;

import com.bsuir.users.service.UserServiceEvdokimovRD;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilterEvdokimovRD extends OncePerRequestFilter {

    private UserServiceEvdokimovRD service;
    private JWTTokenHelperEvdokimovRD jwtTokenHelperEvdokimovRD;

    public JWTAuthenticationFilterEvdokimovRD(UserServiceEvdokimovRD service, JWTTokenHelperEvdokimovRD jwtTokenHelperEvdokimovRD) {
        this.service = service;
        this.jwtTokenHelperEvdokimovRD = jwtTokenHelperEvdokimovRD;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = jwtTokenHelperEvdokimovRD.getToken(request);

        if(null!=authToken) {

            String userName = jwtTokenHelperEvdokimovRD.getUsernameFromToken(authToken);

            if(null!=userName) {

                UserDetails userDetails = service.loadUserByUsername(userName);

                if(jwtTokenHelperEvdokimovRD.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
