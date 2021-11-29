package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.requests.LoginRequest;
import com.bluescripts.globaloffice.office.requests.PasswordresetRequest;
import com.bluescripts.globaloffice.office.responses.CreateLoginResponse;
import com.bluescripts.globaloffice.office.responses.LoginResponse;
import com.bluescripts.globaloffice.office.responses.LoginresetResponse;
import com.bluescripts.globaloffice.office.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PutMapping("/resetpassword")
    public LoginresetResponse resetPassword(@RequestBody PasswordresetRequest request,@RequestParam("emailId") String emailId)
    {
        return loginService.resetPassword(request,emailId);
    }

    @GetMapping("/logout")
    public void logoutpage(@RequestParam(value = "error",required = false) String error,
                           @RequestParam(value = "logout",required = false) String logout)
    {
        ModelAndView model=new ModelAndView();
        if(error != null)
        {
            model.addObject("error","Invalid Credentials");
        }
        if (logout!=null)
        {
            model.addObject("logout","loggingout from office");
        }
        model.setViewName("logoutpage");
    }


}
