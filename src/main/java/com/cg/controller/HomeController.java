package com.cg.controller;

import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;

    @RequestMapping("")
    public String showHomePage(Model model) {

        String username = appUtils.getPrincipalUsername();

        Optional<User> user = userService.findByUsername(username);

        String role = user.get().getRole().getCode();

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        return "index";
    }
}
