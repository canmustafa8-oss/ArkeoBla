package com.arkeobla.controller;

import com.arkeobla.model.ForumComment;
import com.arkeobla.model.ForumTopic;
import com.arkeobla.repository.ForumCommentRepository;
import com.arkeobla.repository.ForumTopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/forum")
public class ForumController {

    private final ForumTopicRepository topicRepository;
    private final ForumCommentRepository commentRepository;
    private final com.arkeobla.service.UserService userService;
    private final com.arkeobla.repository.UserRepository userRepository;

    public ForumController(ForumTopicRepository topicRepository, ForumCommentRepository commentRepository,
            com.arkeobla.service.UserService userService, com.arkeobla.repository.UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listTopics(Model model) {
        model.addAttribute("topics", topicRepository.findAllByOrderByCreatedAtDesc());
        return "forum";
    }

    @GetMapping("/new")
    public String newTopicForm() {
        return "forum-new";
    }

    @PostMapping("/new")
    public String createTopic(@RequestParam String title, @RequestParam String content, @RequestParam String category,
            Principal principal) {
        ForumTopic topic = new ForumTopic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setCategory(category);
        topic.setAuthor(principal.getName());
        topicRepository.save(topic);

        // Award points for topic creation
        userRepository.findByUsername(principal.getName()).ifPresent(user -> {
            userService.addScore(user, 20);
        });

        return "redirect:/forum";
    }

    @GetMapping("/{id}")
    public String viewTopic(@PathVariable Long id, Model model) {
        model.addAttribute("topic", topicRepository.findById(id).orElseThrow());
        return "forum-detail";
    }

    @PostMapping("/reply/{topicId}")
    public String replyToTopic(@PathVariable Long topicId, @RequestParam String content, Principal principal) {
        ForumTopic topic = topicRepository.findById(topicId).orElseThrow();
        ForumComment comment = new ForumComment();
        comment.setContent(content);
        comment.setAuthor(principal.getName());
        comment.setTopic(topic);
        commentRepository.save(comment);

        // Award points for comment
        userRepository.findByUsername(principal.getName()).ifPresent(user -> {
            userService.addScore(user, 5);
        });

        return "redirect:/forum/" + topicId;
    }
}
