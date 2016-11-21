package com.controller;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/dashboard")
    @PreAuthorize("hasAuthority('CREATE_SMTH')")
    public String getDashboardIndex(Model model) {
        return "admin-index";
    }
}
