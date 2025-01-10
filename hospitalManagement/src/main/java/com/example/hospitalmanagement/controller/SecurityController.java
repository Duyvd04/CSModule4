package com.example.hospitalmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "") String error,
                            @RequestParam(value = "logout", defaultValue = "") String logout) {
        if (!error.isEmpty()) {
            model.addAttribute("error", "Invalid username or password.");
        }
        if (!logout.isEmpty()) {
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/403")
    public String view403Page() {
        return "403";
    }

}
