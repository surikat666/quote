package com.strokova.quote.controller;

import com.strokova.quote.model.User;
import com.strokova.quote.repository.QuoteRepository;
import com.strokova.quote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

@Controller
public class ViewController {

    private final UserRepository userRepository;

    private final QuoteRepository quoteRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    @Autowired
    public ViewController(UserRepository userRepository, QuoteRepository quoteRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        notNull(userRepository, "voter Repository must be not null");
        notNull(passwordEncoder, "Password Encoder must be not null");
        notNull(authManager, "authManager must be not null");
        notNull(quoteRepository, "quote Repository must be not null");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.quoteRepository = quoteRepository;
    }

    @GetMapping({"/top10", "/login", "/"})
    public String view(Model model) {
        return getViewName(model, "top10");
    }

    @GetMapping({"/last", "/login"})
    public String viewLast(Model model) {
        return getViewName(model, "last");
    }

    @GetMapping({"/flop10", "/login"})
    public String viewFlop10(Model model) {
        return getViewName(model, "flop10");
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public View signUp(String username, String password, String confirmPassword,  HttpServletRequest request) {
        if (!Objects.equals(password, confirmPassword) || userRepository.exists(username)) {
            return new RedirectView("/signup?error");
        }
        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        return new RedirectView("/top10");
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    private String getViewName(Model model, String dataUrl) {
        model.addAttribute("url", dataUrl);
        return "quotes";
    }
}
