package com.leila.salao.LeilaSalao.config;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.leila.salao.LeilaSalao.user.UserModel;
import com.leila.salao.LeilaSalao.user.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Filter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var serverPath = request.getServletPath();
        if (serverPath.startsWith("/schedule/")) {
            var authorization = request.getHeader("Authorization");
            var authorizationApikey = request.getHeader("apikey");

            // Basic Auth
            if (authorization != null) {
                var authEncoded = authorization.substring(5).trim();
                byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
                var authString = new String(authDecoded);
                String[] credentials = authString.split(":");
        
                var user = this.userRepository.findByUsername(credentials[0]);
                if (user == null) {
                    response.sendError(401);
                } else {
                    var resultPassword = BCrypt.verifyer().verify(credentials[1].toCharArray(), user.getPassword()).verified;
                    if (resultPassword) {
                        request.setAttribute("idUser", user.getId()); // Get in the controllers
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }
                }
            } else if (authorizationApikey != null) {
                UserModel user = userRepository.findByApiKey(UUID.fromString(authorizationApikey));
                if (user != null) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
