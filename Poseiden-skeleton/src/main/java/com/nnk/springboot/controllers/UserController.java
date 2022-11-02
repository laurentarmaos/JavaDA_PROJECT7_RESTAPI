package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.getUserList());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
    	model.addAttribute("user", new User());
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "user/add";
        }
 
        return "redirect:/";
 
    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "user/add";
    	}
    	
    	userService.addUser(user);
    	model.addAttribute("users", userService.getUserList());
    	log.info("new user created : " + user.getUsername() + ", redirect to /user/list");
    	return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        
    	try {
			User user = userService.getById(id);
			model.addAttribute("user", user);
			log.info("get user by id : " + id);
            return "user/update";
		} catch (Exception e) {
			model.addAttribute("errorId", "Invalid bid Id:" + id);
			log.error("error, invalid user id : " + id);
            return "user/update";
		}

    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
        	log.error("error : " + result);
            return "user/update";
        }

        userService.updateUser(user, id);
        log.info("updated user : " + user.getUsername());
        model.addAttribute("users", userService.getUserList());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

    	try {
			userService.deleteUser(id);
			log.info("deleted user with id : " + id);
		} catch (Exception e) {
			log.error("invalid id : " + id);
		}
    	model.addAttribute("users", userService.getUserList());
        return "redirect:/user/list";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/";
    }
    
    
    @GetMapping("/register")
    public String registerForm(Model model) {
    	model.addAttribute("user", new User());
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/register";
        }
 
        return "redirect:/";
    }
    
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "/register";
    	}
    	
    	userService.createUser(user);
    	model.addAttribute("users", userService.getUserList());
    	log.info("new user created : " + user.getUsername() + ", redirect to home");
    	return "redirect:/";
    }
    
    @GetMapping("/403")
    public String accessDenied() {
    	return "/403";
    }
}
