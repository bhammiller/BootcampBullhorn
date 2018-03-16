package com.example.demo.Config;

import com.example.demo.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUDS(appUserRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/**", "/css/**","/fonts/**","/js/**","/img/**","/sass/**").permitAll()
                /*.antMatchers("/userslist","/addeditem/{id}","/processaddedtem","/tofound/{id}",
                        "/tolost/{id}","/addeditem2","/processaddedtem2").hasAuthority("ADMIN")*/
                .anyRequest().authenticated();

        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("brandon").password("password").authorities("ADMIN");
        auth.userDetailsService(userDetailsServiceBean());
    }
}