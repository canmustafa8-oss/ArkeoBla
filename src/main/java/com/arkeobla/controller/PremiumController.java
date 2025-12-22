package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PremiumController {

    @GetMapping("/premium")
    public String premiumPage() {
        return "premium";
    }

    @PostMapping("/premium/subscribe")
    public String subscribe() {
        // Burada gerçek ödeme işlemi ve rol güncellemesi yapılır.
        // Demo olduğu için direkt başarı sayfasına yönlendiriyoruz.
        return "redirect:/premium?success";
    }
}
