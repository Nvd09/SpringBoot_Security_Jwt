package com.example.Security.Controller;

import com.example.Security.Model.User;
import com.example.Security.Service.JwtService;
import com.example.Security.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return registrationService.saveUser(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
                ));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else {
            return "Fail";
        }
    }
}
