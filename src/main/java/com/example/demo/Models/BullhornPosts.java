package com.example.demo.Models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private LocalDateTime createDateTime2;

    private LocalDateTime displayPostTime;

    // Constructors

    public BullhornPosts() {
    }

    public BullhornPosts(String messageText, String messageImage, Integer likeCounter, LocalDateTime createDateTime2,
                         LocalDateTime displayPostTime, AppUser appUser, List<BullhornComments> bullhornCommentsList) {
        this.messageText = messageText;
        this.messageImage = messageImage;
        this.likeCounter = likeCounter;
        this.createDateTime2 = createDateTime2;
        this.displayPostTime = displayPostTime;
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

    public LocalDateTime getCreateDateTime2() {
        return createDateTime2;
    }

    public void setCreateDateTime2(LocalDateTime createDateTime2) {
        this.createDateTime2 = createDateTime2;
    }

    public LocalDateTime getDisplayPostTime() {
        return displayPostTime;
    }

    public void setDisplayPostTime(LocalDateTime displayPostTime) {
        this.displayPostTime = displayPostTime;
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
