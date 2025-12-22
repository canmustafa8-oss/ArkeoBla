package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingController {

    @GetMapping("/meeting")
    public String meetingPage(java.security.Principal principal, org.springframework.ui.Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "meeting";
    }
}
