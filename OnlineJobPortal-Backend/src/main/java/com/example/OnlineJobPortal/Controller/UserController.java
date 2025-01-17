package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable int id){
        Users users = userService.getUsersById(id);
        if(users != null)
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/users/{email}")
    public ResponseEntity<Users> getUsersById(@PathVariable String email){
        Users users = userService.getUsersByEmail(email);
        if(users != null)
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        Users users = userService.getUsersById(id);
        if (users != null) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User successfully deleted.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateJob(@PathVariable int id, @RequestBody Users users){
        Users users1 = userService.updateUser(id, users);
        if (users1 != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("failed to update", HttpStatus.NOT_FOUND);
    }



}
