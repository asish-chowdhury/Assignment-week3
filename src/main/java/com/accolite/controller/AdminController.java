package com.accolite.controller;

import com.accolite.entity.Admin;
import com.accolite.entity.UserInfo;
import com.accolite.service.AdminService;
import com.accolite.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin added successfully");
    }

//    @PostMapping("/approveUserRegistration")
//    public ResponseEntity<String> approveUserRegistration(@RequestParam String username) {
//        userInfoService.updateRegistrationStatus(username);
//        return ResponseEntity.ok("User registration approved");
//    }

    @PostMapping("/approveUserRegistration/{username}")
    public ResponseEntity<String> approveUserRegistration(@PathVariable String username) {
        userInfoService.updateRegistrationStatus(username);
        return ResponseEntity.ok("User registration approved");
    }


    @GetMapping("/findAllUserNames")
    public ResponseEntity<List<UserInfo>> findAllUserNames() {
        List<UserInfo> userNames = adminService.findAllUserNames();
        return ResponseEntity.ok(userNames);
    }
    @GetMapping("/findAllUsersWithNullRegistrationStatus")
    public ResponseEntity<List<UserInfo>> findAllUsersWithNullRegistrationStatus() {
        List<UserInfo> users = adminService.findAllUsersWithNullRegistrationStatus();
        return ResponseEntity.ok(users);
    }

}
