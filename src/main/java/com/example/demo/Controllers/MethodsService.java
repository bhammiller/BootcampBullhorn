package com.example.demo.Controllers;

import com.example.demo.Models.AppUser;
import com.example.demo.Models.BullhornComments;
import com.example.demo.Models.BullhornPosts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MethodsService {

    public List<BullhornPosts> messagesBackwards(AppUser appUser){
        List<BullhornPosts> inputListed = appUser.getBullhornPostsList();
        List<BullhornPosts> displayMessages = new ArrayList<>();
        for (int i =inputListed.size()-1;i>=0;i--){
            displayMessages.add(inputListed.get(i));
        }
        return displayMessages;
    }

    public List<BullhornComments> commentsBackwards(BullhornPosts bullhornPosts){
        List<BullhornComments> inputListed= bullhornPosts.getBullhornCommentsList();
        List<BullhornComments> displayComments = new ArrayList<>();
        for (int i =inputListed.size()-1;i>=0;i--){
            displayComments.add(inputListed.get(i));
        }
        return displayComments;
    }
}
