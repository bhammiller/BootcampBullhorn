package com.example.demo.Config;

import com.example.demo.Repositories.*;
import com.example.demo.Models.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class SSUDS implements UserDetailsService {
    private AppUserRepository appUserRepository;

    public SSUDS(AppUserRepository userRepository) {
        this.appUserRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AppUser thisUser = appUserRepository.findAppUserByAppUsername(username);


        if(thisUser==null)
            throw new UsernameNotFoundException("Invalid username or password");


        return new User(thisUser.getAppUsername(),thisUser.getAppPassword(),grantedAuthorities(thisUser));
    }

    public Set <GrantedAuthority> grantedAuthorities(AppUser user)
    {
        Set <GrantedAuthority> userAuthorities = new HashSet<>();
        for(AppRole eachRole: user.getRoles())
        {
            userAuthorities.add(new SimpleGrantedAuthority(eachRole.getRoleName()));
        }
        return userAuthorities;
    }
}