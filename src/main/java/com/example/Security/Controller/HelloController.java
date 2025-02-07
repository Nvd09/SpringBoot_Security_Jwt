package com.example.Security.Controller;

import com.example.Security.Model.User;
import com.example.Security.Model.UserPrincipal;
import com.example.Security.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/hello")
    public String greet() {
        String username = userDetailsService.getUser().getUsername();
        return "Hello, " + username;
    }

    @GetMapping("/about")
    public String about(HttpServletRequest request) {
        return "Navid's security project " + request.getSession().getId();
    }
}
