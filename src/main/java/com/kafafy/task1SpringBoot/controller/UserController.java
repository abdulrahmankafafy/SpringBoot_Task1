package com.kafafy.task1SpringBoot.controller;

import com.kafafy.task1SpringBoot.model.UserModel;
import com.kafafy.task1SpringBoot.model.UserError;
import com.kafafy.task1SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<UserError> addUser(@RequestBody UserModel userModel) {
        UserError result = userService.addUser(userModel);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(result.getErrorCode()).body(result);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserError> updateUser(@PathVariable int id, @RequestBody UserModel userModel) throws SQLException {
        UserError result = userService.updateUser(id, userModel);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(result.getErrorCode()).body(result);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserError> deleteUser(@PathVariable int id) {
        UserError result = userService.deleteUser(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(result.getErrorCode()).body(result);
        }
    }
}
