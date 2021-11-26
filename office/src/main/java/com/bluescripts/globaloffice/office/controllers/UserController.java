package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.entity.User;
import com.bluescripts.globaloffice.office.requests.UserRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.UserResponse;
import com.bluescripts.globaloffice.office.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserResponse getUser(@RequestParam String userId)
    {
        return userService.getUser(userId);
    }

    @PostMapping
    public GenericResponse createUser(@RequestBody UserRequest request)
    {
        return userService.createUser(request);
    }

    @DeleteMapping("/{userId}")
    public GenericDeleteResponse deleteUser(@RequestParam String userId)
    {
        return userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public GenericResponse updateUser(@RequestBody UserRequest request,@RequestParam String userId)
    {
        return userService.updateUser(request,userId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public Iterable<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
