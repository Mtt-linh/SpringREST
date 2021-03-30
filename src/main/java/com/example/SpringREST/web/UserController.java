package com.example.SpringREST.web;


import com.example.SpringREST.model.User;
import com.example.SpringREST.service.SecurityService;
import com.example.SpringREST.service.UserService;
import com.example.SpringREST.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult)
    {
        userValidator.validate(userForm,bindingResult);
        if (bindingResult.hasErrors())
        {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }


    @RequestMapping(path = "/login")
    public String login(Model model,String error, String logout)
    {
        if (error != null)
        {
            model.addAttribute("error","You username and password is invalid.");
        }
        if (logout != null)
        {
            model.addAttribute("message","You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping({"/","/welcome"})
    public String welcome(Model model)
    {
        return "welcome";
    }



}