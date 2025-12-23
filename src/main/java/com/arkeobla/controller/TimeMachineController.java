package com.arkeobla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TimeMachineController {

                public String id;
                public String title;
                public String period;
                public String description;
                public String imageUrl;
                public String detailedContent;

                public Era(String id, String title, String period, String description, String imageUrl) {
                        this.id = id;
                        this.title = title;
                        this.period = period;
                        this.description = description;
                        this.imageUrl = imageUrl;
                }

                public void setDetailedContent(String content) {
                        this.detailedContent = content;
                }

                public String getDetailedContent() {
                        return detailedContent;
                }

                // Getters for Thymeleaf
                public String getId() {
                        return id;
                }

                public String getTitle() {
                        return title;
                }

                public String getPeriod() {
                        return period;
                }

                public String getDescription() {
                        return description;
                }

                public String getImageUrl() {
                        return imageUrl;
                }
        }
}
