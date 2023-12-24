package com.accolite.service;

import com.accolite.entity.UserInfo;
import com.accolite.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    public void updateUserToken(String username, String token) {
        Optional<UserInfo> optionalUserInfo = repository.findByUsername(username);
        optionalUserInfo.ifPresent(userInfo -> {
            userInfo.setToken(token);
            repository.save(userInfo);
        });
    }


    public void updateRegistrationStatus(String username) {
        Optional<UserInfo> userInfoOptional = repository.findByUsername(username);
        userInfoOptional.ifPresent(userInfo -> {
            userInfo.setRegistrationStatus(true);
            repository.save(userInfo);
        });
    }

    public List<String> findAllUserNames() {
        List<String> names = new ArrayList<>();
        for(UserInfo info: repository.findAll()){
            names.add(info.getName());
        }
        return names;
    }

    //----
    public Optional<UserInfo> getUserByUsername(String username) {
        return repository.findByUsername(username);
    }
}