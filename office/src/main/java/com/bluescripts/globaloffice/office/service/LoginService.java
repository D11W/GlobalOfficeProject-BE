package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.entity.Login;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.LoginRepo;
import com.bluescripts.globaloffice.office.requests.LoginRequest;
import com.bluescripts.globaloffice.office.responses.CreateLoginResponse;
import com.bluescripts.globaloffice.office.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

   private final LoginRepo loginRepo;
   private final ModelMapper modelMapper;
   private final PasswordEncoder passwordEncoder;

   public CreateLoginResponse createLogin(LoginRequest request)
   {
       Login login = new Login();
       login.setUserName(request.getUserName());
       login.setPassword(request.getPassword());
//       login.setRole(request.getRole());
        loginRepo.save(login);
       CreateLoginResponse loginResponse=new CreateLoginResponse();
       loginResponse.setMessage("login created successfully");
       return loginResponse;
   }

    public LoginResponse getLogin(String userName)
    {
        Login login=loginRepo.findByUserName(userName).orElseThrow(()->{
            log.error("login userName not found :{}",userName);
            return new NoRecordFoundException("login user is not found" +userName);
        });
        return modelMapper.map(login,LoginResponse.class);
    }

    public LoginResponse updateLogin(LoginRequest request, String userName)
    {
        Login login= loginRepo.findByUserName(userName).orElseThrow(()->{
            log.error("login userName not found :{}",userName);
            return new NoRecordFoundException("login user is not found" +userName);
        });
        login.setUserName(request.getUserName());
        login.setPassword(request.getPassword());
        loginRepo.save(login);
        return modelMapper.map(login,LoginResponse.class);
    }

    public void deleteLogin(String userName)
    {
        Login login = loginRepo.findByUserName(userName).orElseThrow(()->
        {
            log.error("delete the user"+userName);
            return new NoRecordFoundException("deleted user"+userName);
        });
        loginRepo.delete(login);
    }


}
