package com.example.demo.Models;

import com.example.demo.Repositories.BullhornPostsRepository;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class BullhornComments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String commentText;

    private String commentMaker;

    @CreationTimestamp
    Timestamp createdAt;

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Constructors

    public BullhornComments() {
    }

    public BullhornComments(String commentText, String commentMaker, Timestamp createdAt, BullhornPosts bullhornPosts) {
        this.commentText = commentText;
        this.commentMaker = commentMaker;
        this.createdAt = createdAt;
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
