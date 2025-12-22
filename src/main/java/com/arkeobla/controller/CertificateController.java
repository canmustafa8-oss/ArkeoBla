package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CertificateController {

    @GetMapping("/certificate")
    public String certificatePage() {
        return "certificate";
    }
}
