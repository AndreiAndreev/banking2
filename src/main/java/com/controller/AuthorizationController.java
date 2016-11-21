package com.controller;

import com.exeptions.DuplicateUserException;
import com.exeptions.RoleNotFoundException;
import com.exeptions.UserNotFoundException;
import com.model.Role;
import com.model.User;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/sign-in")
    public String getLoginPage() {
        return "sign-in";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String userSave(@Valid @ModelAttribute User user, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("serverErrorMessage", "Please, try again later");
            return "redirect:/sign-up";
        } else {
            System.out.println(user);
            user.setEnabled(true);
            try {
                userService.addUser(user);
            } catch (DuplicateUserException e) {
                e.printStackTrace();
            }
            return "redirect:/";
        }
    }
}
