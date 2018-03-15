package com.example.demo.Models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class BullhornPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String messageText;

    private String messageImage;

    private Integer likeCounter;

    @CreationTimestamp
    Timestamp createdAt;

    // Constructors

    public BullhornPosts() {
    }

    public BullhornPosts(String messageText, String messageImage,
                         Integer likeCounter, Timestamp createdAt, AppUser appUser, List<BullhornComments> bullhornCommentsList) {
        this.messageText = messageText;
        this.messageImage = messageImage;
        this.likeCounter = likeCounter;
        this.createdAt = createdAt;
        this.appUser = appUser;
        this.bullhornCommentsList = bullhornCommentsList;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(String messageImage) {
        this.messageImage = messageImage;
    }

    public Integer getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(Integer likeCounter) {
        this.likeCounter = likeCounter;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    // CONNECTIONS
    // Connection to AppUser
    @ManyToOne
    @JoinColumn
    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    // Connection to Comments
    @OneToMany
    private List<BullhornComments> bullhornCommentsList;

    public void addBullhornComment(BullhornComments bullhornComments){
        this.bullhornCommentsList.add(bullhornComments);
    }

    public List<BullhornComments> getBullhornCommentsList() {
        return bullhornCommentsList;
    }

    public void setBullhornCommentsList(List<BullhornComments> bullhornCommentsList) {
        this.bullhornCommentsList = bullhornCommentsList;
    }
}
