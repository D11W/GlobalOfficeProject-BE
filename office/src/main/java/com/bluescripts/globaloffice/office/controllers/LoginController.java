package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.requests.LoginRequest;
import com.bluescripts.globaloffice.office.responses.CreateLoginResponse;
import com.bluescripts.globaloffice.office.responses.LoginResponse;
import com.bluescripts.globaloffice.office.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/userName")
    public CreateLoginResponse createLogin(@RequestBody LoginRequest loginRequest){
        return loginService.createLogin(loginRequest);
    }

    @GetMapping("/userName")
    public LoginResponse getLogin(@RequestParam("userName") String userName)
    {
        return loginService.getLogin(userName);
    }

    @PutMapping("/userName")
    public LoginResponse updateLogin(@RequestBody LoginRequest loginRequest,@RequestParam("userName") String userName)
    {
        return loginService.updateLogin(loginRequest,userName);
    }

    @DeleteMapping("/userName")
    public void deleteLogin(@RequestParam("userName") String userName)
    {
        loginService.deleteLogin(userName);
    }



}
