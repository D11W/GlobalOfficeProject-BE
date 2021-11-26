package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Login;
import com.bluescripts.globaloffice.office.entity.User;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.LoginRepo;
import com.bluescripts.globaloffice.office.repository.UserRepo;
import com.bluescripts.globaloffice.office.requests.UserRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.UserResponse;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoginRepo loginRepo;


    public GenericResponse createUser(UserRequest userRequest)
    {
        String userId= IdUtils.generateId(StringConstant.userId_prefix);
        String password= userRequest.getPassword();
        String role="USER";

        GenericResponse response = new GenericResponse();
        User user=new User();
        user.setUserId(userId);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUserName(userRequest.getUserName());
        user.setCompany_name(userRequest.getCompany_name());
        user.setAddress(userRequest.getAddress());
//        user.setPassword(userRequest.getPassword());
        user.setContactNo(userRequest.getContactNo());
        user.setDob(userRequest.getDob());

        if(password != null)
        {
            Login login = new Login();
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            login.setUserName(userRequest.getEmailId());
            login.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            login.setRole(role);
            loginRepo.save(login);
            userRepo.save(user);
            response.setMessage("User is created successfully");
        }
        else
        {
            response.setMessage("please enter the correct password"+password);
        }
        return response;
    }

    public UserResponse getUser(String userId)
    {
        User user=userRepo.findByUserId(userId).orElseThrow(()-> {
            return new NoRecordFoundException("No User Found"+userId);
        });
        return modelMapper.map(user,UserResponse.class);
    }

    public GenericDeleteResponse deleteUser(String userId)
    {
        User user=userRepo.findByUserId(userId).orElseThrow(()->
        {
            return new NoRecordFoundException("record is deleted"+userId);
        });
        GenericDeleteResponse deleteResponse = new GenericDeleteResponse(userId,true);
//        deleteResponse.setDeleted(true);
        user.setDelete(true);
        userRepo.save(user);
        return deleteResponse;
    }

    public Iterable<User> getAllUsers()
    {
        return userRepo.findAll();
    }

    public GenericResponse updateUser(UserRequest userRequest,String userId)
    {
        User user=userRepo.findByUserId(userId).orElseThrow(()->
        {
           return new NoRecordFoundException("NO Record Found"+userId);
        });
        user.getUserId();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCompany_name(userRequest.getCompany_name());
        user.setDob(userRequest.getDob());
        user.setAddress(userRequest.getAddress());
        user.setContactNo(userRequest.getContactNo());
//        user.setRole(userRequest.getRole());
        user.setPassword(userRequest.getPassword());

        userRepo.save(user);

        GenericResponse updateResponse= new GenericResponse();
        updateResponse.setMessage("Record Is Updated Successfully");
        return updateResponse;
    }

}
