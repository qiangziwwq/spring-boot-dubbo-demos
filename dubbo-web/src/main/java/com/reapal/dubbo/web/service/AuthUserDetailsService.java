package com.reapal.dubbo.web.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.reapal.dubbo.api.model.User;
import com.reapal.dubbo.api.model.UserRole;
import com.reapal.dubbo.api.service.UserRoleService;
import com.reapal.dubbo.api.service.UserService;
import com.reapal.dubbo.web.util.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack-cooper on 2017/1/15.
 */

@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Reference
    private UserService userService;
    @Reference
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user;
        try {
            user =new User() ;//userService.queryByName(name);
            user.setUsername("123");
            user.setPassword("$2a$10$SBKqI0/wkov4gXvUhM.Lw.hlodX46Ag3uwj/5HJRKbW9.W6argixq");
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if(user == null){
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                List<UserRole> roles =new ArrayList<>();// userRoleService.getRoleByUser(user);
                UserRole userRole = new UserRole();
                userRole.setUserId(8L);
                userRole.setRole("admin");
                roles.add(userRole);
                return new MyUserDetails(user, roles);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }
}
