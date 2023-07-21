package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(ModelMap model) {
        logger.info("getAll");
        model.addAttribute("users", service.getAll());
        return "pages/main";
    }

    @PostMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        logger.info("delete");
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String getForm(@RequestParam(required = false) Long modifiedUserId, ModelMap model) {
        logger.info("getForm");
        User user = Objects.isNull(modifiedUserId) ? new User() : service.get(modifiedUserId);
        model.addAttribute("user", user);
        return "pages/form";
    }

    @PostMapping("/users")
    public String processForm(User user) {
        logger.info("processForm");
        service.save(user);
        return "redirect:/";
    }
}
