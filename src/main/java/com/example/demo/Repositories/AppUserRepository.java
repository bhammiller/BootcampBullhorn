package com.example.demo.Repositories;

import com.example.demo.Models.AppUser;
import com.example.demo.Models.AppRole;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserByAppUsername(String username);
    HashSet <AppUser> findByRoles(AppRole r);

}
