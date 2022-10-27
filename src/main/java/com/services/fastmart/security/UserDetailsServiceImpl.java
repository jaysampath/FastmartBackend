package com.services.fastmart.security;

import com.services.fastmart.entity.User;
import com.services.fastmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(String.format("email - %s is not registered. Please sign up", email));
        }
        return convertUserToUserDetails(user);
    }

    private UserDetailsImpl convertUserToUserDetails(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setEmail(user.getUserEmail());
        userDetails.setPassword(user.getPassword());
        userDetails.setUsername(user.getUserName());
        userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
        return userDetails;
    }
}