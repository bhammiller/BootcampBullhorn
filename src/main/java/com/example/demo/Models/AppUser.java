package com.example.demo.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotEmpty
    @Column(unique = true)
    private String appUsername;

    @NotEmpty
    private String appPassword;

    private String firstName;

    private String lastName;

    @Email
    private String userEmail;

    @NotNull
    @NotEmpty
    private String displayName;

    private String displayImage;

    @CreationTimestamp
    Timestamp createdAt;

    // Follow other Users
    @Transient
    private List<AppUser> followedUsers;

    public AppUser(List<AppUser> followedUsers, List<BullhornPosts> bullhornPostsList) {
        this.followedUsers = followedUsers;
        this.bullhornPostsList = bullhornPostsList;
    }

    public void addFollowedUser(AppUser appUser){
        this.followedUsers.add(appUser);
    }

    public List<AppUser> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<AppUser> followedUsers) {
        this.followedUsers = followedUsers;
    }

    // Variable Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    // Connection to AppRole
    @ManyToMany(fetch = FetchType.EAGER)
    //This needs to be instantiated in the construtor so you can use it to add and remove individual roles
    private Set<AppRole> roles;

    public AppUser() {
        this.roles = new HashSet<>();
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public void addRole(AppRole role) {
        this.roles.add(role);
    }

    // Connection to BullhornPosts
    @OneToMany(mappedBy = "appUser")
    private List<BullhornPosts> bullhornPostsList;

    public void addBullhornPPost(BullhornPosts bullhornPosts){
        this.bullhornPostsList.add(bullhornPosts);
    }

    public List<BullhornPosts> getBullhornPostsList() {
        return bullhornPostsList;
    }

    public void setBullhornPostsList(List<BullhornPosts> bullhornPostsList) {
        this.bullhornPostsList = bullhornPostsList;
    }

    /*// Connection to LostItems
    @OneToMany(mappedBy = "appUser")
    private List<LostItems> lostItemsList;

    public void addLostItems(LostItems lostItems) {
        this.lostItemsList.add(lostItems);
    }

    public AppUser(List<LostItems> lostItemsList) {
        this.lostItemsList = lostItemsList;
    }

    public List<LostItems> getLostItemsList() {
        return lostItemsList;
    }

    public void setLostItemsList(List<LostItems> lostItemsList) {
        this.lostItemsList = lostItemsList;
    }*/
}