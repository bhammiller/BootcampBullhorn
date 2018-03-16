package com.example.demo.Models;

import com.example.demo.Repositories.BullhornPostsRepository;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class BullhornComments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String commentText;

    private String commentMaker;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private LocalDateTime displayCommentTime;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentMaker() {
        return commentMaker;
    }

    public void setCommentMaker(String commentMaker) {
        this.commentMaker = commentMaker;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getDisplayCommentTime() {
        return displayCommentTime;
    }

    public void setDisplayCommentTime(LocalDateTime displayCommentTime) {
        this.displayCommentTime = displayCommentTime;
    }

// Constructors

    public BullhornComments() {
    }

    public BullhornComments(String commentText, String commentMaker,
                            LocalDateTime createDateTime, LocalDateTime displayCommentTime, BullhornPosts bullhornPosts) {
        this.commentText = commentText;
        this.commentMaker = commentMaker;
        this.createDateTime = createDateTime;
        this.displayCommentTime = displayCommentTime;
        this.bullhornPosts = bullhornPosts;
    }

    // CONNECTIONS
    // Connection to BullhornPosts
    @ManyToOne
    @JoinColumn
    private BullhornPosts bullhornPosts;

    public BullhornPosts getBullhornPosts() {
        return bullhornPosts;
    }

    public void setBullhornPosts(BullhornPosts bullhornPosts) {
        this.bullhornPosts = bullhornPosts;
    }
}
