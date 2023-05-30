package com.dsw.trabalho.minimalList.helper;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthHelper {
    private HttpServletRequest request;
    private UserRepository userRepository;

    public boolean isAuthenticated() {
        HttpSession httpSession = request.getSession(false);

        if (httpSession == null) {
            return false;
        }
        
        User user = (User) httpSession.getAttribute("user");

        if (user != null && user.getId() == null && user.getEmail() == null && user.getToken() == null) {
            httpSession.invalidate();
            return false;
        }

        // check id, token and email
        Optional<User> userValidated = userRepository.findByAuthenticatedUser(user.getId(), user.getEmail(), user.getToken());
        if (!userValidated.isPresent()) {
            httpSession.invalidate();
            return false;
        }

        return true;
    }

    public String generateToken() {
        SecureRandom secureRandom = new SecureRandom(); //threadsafe
        Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = (String) base64Encoder.encodeToString(randomBytes);

        return token;
    }
}
