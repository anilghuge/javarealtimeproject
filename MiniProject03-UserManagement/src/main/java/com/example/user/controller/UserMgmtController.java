package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user.binding.ActivateUser;
import com.example.user.binding.LoginCredentials;
import com.example.user.binding.RecoverPassword;
import com.example.user.binding.UserAccount;
import com.example.user.service.IUserMgmtService;

@RestController
@RequestMapping("/users/api")
public class UserMgmtController {

    @Autowired
    private IUserMgmtService userMgmtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAccount account) {
        try {
            String response = userMgmtService.registerUser(account);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateUserAccount(@RequestBody ActivateUser activateUser) {
        String response = userMgmtService.activateUserAccount(activateUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials credentials) {
        String response = userMgmtService.login(credentials);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listusers")
    public ResponseEntity<List<UserAccount>> listUsers() {
        List<UserAccount> users = userMgmtService.listUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> showUserByUserId(@PathVariable Integer id) {
        UserAccount account = userMgmtService.showUserByUserId(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<UserAccount> showUserByEmailAndName(@RequestParam String email, @RequestParam String name) {
        UserAccount account = userMgmtService.showUserByEmailAndName(email, name);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserAccount user) {
        String response = userMgmtService.updateUser(user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        String response = userMgmtService.deleteUserById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/changestatus/{id}/{status}")
    public ResponseEntity<String> changeUserStatus(@PathVariable Integer id, @PathVariable String status) {
        String response = userMgmtService.changeUserStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestBody RecoverPassword recoverPassword) {
        try {
            String response = userMgmtService.recoverPassword(recoverPassword);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error recovering password: " + e.getMessage());
        }
    }
}
