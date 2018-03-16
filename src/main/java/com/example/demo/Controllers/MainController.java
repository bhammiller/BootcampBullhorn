package com.example.demo.Controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Models.*;
import com.example.demo.Repositories.*;

import javax.validation.Valid;

@Controller
public class MainController {
    // VARIABLES
    // Autowired Repositories
    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    BullhornCommentsRepository bullhornCommentsRepository;

    @Autowired
    BullhornPostsRepository bullhornPostsRepository;

    @Autowired
    MethodsService methodsService;
    // Global Variables
    BullhornPosts bullhornPosts1;
    AppUser appUser1;
    // METHODS
    // General Methods
    @RequestMapping("/")
    public String index() {
        return "homepage";
    }

    @RequestMapping("/login")
    public String login() {
        return "loginpage";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("newUser", new AppUser());
        return "registrationpage";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute("newUser") AppUser newUser, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registrationpage";
        } else {

            model.addAttribute(newUser.getAppUsername() + " created");
            appUserRepository.save(newUser);
            AppRole r = appRoleRepository.findAppRoleByRoleName("USER");
            newUser.addRole(r);
            appUserRepository.save(newUser);
            return "redirect:/login";
        }
    }
    // User Methods

    @GetMapping("/addmessage")
    public String addMessage(Model model){
        model.addAttribute("addmessage", new BullhornPosts());
        return "addmessagepage";
    }
    @PostMapping("/adddmessage")
    public String processMessageg(BindingResult result, Model model){
        if (result.hasErrors()){
            return "addmessagepage";
        }else {
            return "";
        }
    }

    @RequestMapping("/userpage/{id}")
    public String showUserPosts(@PathVariable("id") long id, Model model){
        model.addAttribute("usersposts",appUserRepository.findOne(id));
        return "userpostspage";
    }


    public String likeMessage(){
        return "";
    }

    @GetMapping("/message/{id}")
    public String addComment(@PathVariable("id") long id,Model model){
        bullhornPosts1=bullhornPostsRepository.findOne(id);
        model.addAttribute("showmessage",bullhornPosts1);
        model.addAttribute("showcomments",bullhornPosts1);
        model.addAttribute("addcomment",new BullhornComments());
        return "messagepage";
    }

    public String processComment(@Valid @ModelAttribute("addcomment") BullhornComments bullhornComments,
                                 @PathVariable("id") long id, BindingResult result, Model model,
                                 Authentication authentication){
        if (result.hasErrors()){
            model.addAttribute("showmessage",bullhornPosts1);
            model.addAttribute("showcomments",bullhornPosts1);
            return "messagepage";
        }else{


            return "redirect:/message/{id}";
        }
    }

    @RequestMapping("/userlist")
    public String showAllUsers(Model model){
        model.addAttribute("userlist", appUserRepository.findAll());
        return"userlistpage";
    }

    @RequestMapping("/follow/{id}")
    public String followUser(@PathVariable("id")long id){
        return "redirect:/userpage/{id}";
    }
}
