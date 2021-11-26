package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.entity.Login;
import com.bluescripts.globaloffice.office.model.UserDetailsImp;
import com.bluescripts.globaloffice.office.repository.LoginRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final LoginRepo loginRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserDetailsImp loadUserByUsername(String userName) throws UsernameNotFoundException {

        Login login = loginRepo.findByUserName(userName).orElseThrow( () ->
                new UsernameNotFoundException("not found" + userName));
        return modelMapper.map(login, UserDetailsImp.class);
    }
}
