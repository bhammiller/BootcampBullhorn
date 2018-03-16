package com.example.demo.Config;

import com.example.demo.Models.AppRole;
import com.example.demo.Models.AppUser;
import com.example.demo.Repositories.AppRoleRepository;
import com.example.demo.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;



    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data ...");
        AppRole role = new AppRole();
        role.setRoleName("USER");
        appRoleRepository.save(role);

        role = new AppRole();
        role.setRoleName("ADMIN");
        appRoleRepository.save(role);

        // Users
        // User 1
        AppUser appUser = new AppUser();
        appUser.setFirstName("John");
        appUser.setLastName("Doe");
        appUser.setUserEmail("g1@gmail.com");
        appUser.setAppUsername("john");
        appUser.setAppPassword("password1");
        appUser.setDisplayName("Johnny");
        appUserRepository.save(appUser);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);
        /*// User 2
        appUser = new AppUser();
        appUser.setFirstName("Jacob");
        appUser.setLastName("Smith");
        appUser.setUserEmail("g2@gmail.com");
        appUser.setAppUsername("jacob");
        appUser.setAppPassword("password2");
        appUserRepository.save(appUser);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);*/
        // User 3
        appUser = new AppUser();
        appUser.setFirstName("Jane");
        appUser.setLastName("Pane");
        appUser.setUserEmail("g3@gmail.com");
        appUser.setAppUsername("jane");
        appUser.setAppPassword("password3");
        appUser.setDisplayName("Painkiller");
        appUserRepository.save(appUser);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);





    }
}