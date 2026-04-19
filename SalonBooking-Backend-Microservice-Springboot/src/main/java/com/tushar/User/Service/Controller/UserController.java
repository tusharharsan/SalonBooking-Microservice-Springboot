package com.tushar.User.Service.Controller;

import com.tushar.User.Service.Model.User;
import com.tushar.User.Service.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @PutMapping("/updateUser/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user ,
                           @PathVariable Long id) {
        User updatesUser = userService.updateUser(user, id);
        return new ResponseEntity<>(updatesUser , HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
       userService.deleteUser(id);
       return new ResponseEntity<>("User Deleted " , HttpStatus.OK);
    }

}
