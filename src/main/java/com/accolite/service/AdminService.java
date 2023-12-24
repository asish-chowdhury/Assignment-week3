package com.accolite.service;

import com.accolite.entity.Admin;
import com.accolite.entity.UserInfo;
import com.accolite.repository.AdminRepository;
import com.accolite.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserInfoRepository userRepository;

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void approveUserRegistration(String userId) {
//        userRepository.findByUsername(userId).setRegistrationStatus(true);
        Optional<UserInfo> optionalUserInfo = userRepository.findByUsername(userId);
    }

    public List<UserInfo> findAllUserNames() {
        return userRepository.findAll();
    }

    public List<UserInfo> findAllUsersWithNullRegistrationStatus() {
        return userRepository.findAllByRegistrationStatusIsNull();
    }

}