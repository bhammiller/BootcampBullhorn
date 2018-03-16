package com.example.demo.Controllers;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Config.CloudinaryConfig;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.Map;

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

    @Autowired
    CloudinaryConfig cloudc;

    // Global Variables
    BullhornPosts bullhornPosts1;
    AppUser appUser1;
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm - MMM dd, yyyy");
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
    @PostMapping("/addmessage")
    public String processMessageg(@ModelAttribute("addmessage") BullhornPosts bullhornPosts, BindingResult result,
                                  Model model, Authentication authentication,
                                  @RequestParam("file")MultipartFile file){
        if (result.hasErrors()){
            return "addmessagepage";
        }else {
            AppUser appUser = appUserRepository.findAppUserByAppUsername(authentication.getName());
            bullhornPosts.setCreateDateTime2(LocalDateTime.now());
            bullhornPosts.setLikeCounter(0);
            bullhornPosts.setAppUser(appUser);
            bullhornPostsRepository.save(bullhornPosts);
            bullhornPosts.setDisplayPostTime(bullhornPosts.getCreateDateTime2().format(formatter));
            bullhornPostsRepository.save(bullhornPosts);
            appUser.addBullhornPPost(bullhornPosts);
            appUserRepository.save(appUser);
            // format date here
            return "redirect:/";
        }
    }

    @RequestMapping("/userpage/{id}")
    public String showUserPosts(@PathVariable("id") long id, Model model){
        model.addAttribute("usersposts",appUserRepository.findOne(id));
        model.addAttribute("displayedPosts",methodsService.messagesBackwards(appUserRepository.findOne(id)));
        return "userpostspage";
    }


    @RequestMapping("/likepost/{id}")
    public String likeMessage(@PathVariable("id") long id){
        BullhornPosts bullhornPosts =bullhornPostsRepository.findOne(id);
        bullhornPosts.setLikeCounter(bullhornPosts
        .getLikeCounter() + 1);
        bullhornPostsRepository.save(bullhornPosts);
        return "redirect:/message/{id}";
    }

    @GetMapping("/message/{id}")
    public String addComment(@PathVariable("id") long id,Model model){
        bullhornPosts1=bullhornPostsRepository.findOne(id);
        model.addAttribute("showmessage",bullhornPosts1);
        model.addAttribute("showcomments",methodsService.commentsBackwards(bullhornPosts1));
        model.addAttribute("addcomment",new BullhornComments());
        return "messagepage";
    }

    @PostMapping("/processmessage")
    public String processComment(@Valid @ModelAttribute("addcomment") BullhornComments bullhornComments,
                                  BindingResult result, Model model,
                                 Authentication authentication){
        if (result.hasErrors()){
            model.addAttribute("showmessage",bullhornPosts1);
            model.addAttribute("showcomments",methodsService.commentsBackwards(bullhornPosts1));
            return "messagepage";
        }else{
            // Format date here
            AppUser appUser = appUserRepository.findAppUserByAppUsername(authentication.getName());
            bullhornComments.setCreateDateTime(LocalDateTime.now());
            bullhornComments.setCommentMaker(appUser.getDisplayName());
            bullhornComments.setBullhornPosts(bullhornPosts1);
            bullhornCommentsRepository.save(bullhornComments);
            bullhornComments.setDisplayCommentTime(bullhornComments.getCreateDateTime().format(formatter));
            bullhornCommentsRepository.save(bullhornComments);
            bullhornPosts1.addBullhornComment(bullhornComments);
            bullhornPostsRepository.save(bullhornPosts1);
            long id=bullhornPosts1.getId();
            return "redirect:/userlist";
        }
    }

    @RequestMapping("/userlist")
    public String showAllUsers(Model model){
        model.addAttribute("userlist", appUserRepository.findAll());
        return"userlistpage";
    }

    @RequestMapping("/follow/{id}")
    public String followUser(@PathVariable("id")long id,Authentication authentication){
        AppUser appUser = appUserRepository.findAppUserByAppUsername(authentication.getName());
        AppUser appUser2= appUserRepository.findOne(id);
        appUser.addFollowedUser(appUser2);
        appUserRepository.save(appUser);
        return "redirect:/";
    }

    @RequestMapping("/editprofile")
    public String editProfile(Model model, Authentication authentication){
        AppUser appUser = appUserRepository.findAppUserByAppUsername(authentication.getName());
        model.addAttribute("newUser",appUser);
        return "registrationpage";
    }
}
