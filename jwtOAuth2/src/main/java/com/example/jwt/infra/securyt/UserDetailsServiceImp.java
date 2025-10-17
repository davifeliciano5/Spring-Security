package com.example.jwt.infra.securyt;

import com.example.jwt.model.UsejwtRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UsejwtRepository usejwtRepository;
    public UserDetailsServiceImp(UsejwtRepository usejwtRepository){
        this.usejwtRepository = usejwtRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usejwtRepository.findByUsername(username)
                .map(UserAuthenticated::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

    }
}
