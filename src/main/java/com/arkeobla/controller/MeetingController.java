package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingController {

    @GetMapping("/meeting")
    public String meetingPage() {
        return "meeting";
    }
}
