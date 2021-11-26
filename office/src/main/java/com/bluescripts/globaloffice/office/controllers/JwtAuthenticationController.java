package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.model.UserDetailsImp;
import com.bluescripts.globaloffice.office.requests.JWTRequest;
import com.bluescripts.globaloffice.office.responses.JWTResponse;
import com.bluescripts.globaloffice.office.service.MyUserDetailsService;
import com.bluescripts.globaloffice.office.utils.JWTUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {
    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService myUserDetailsService;


    @PostMapping("/authenticate")
    public JWTResponse createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
        final UserDetailsImp userDetailsImp = myUserDetailsService.loadUserByUsername(
                authenticationRequest.getUserName());
        final String token = "Bearer " + jwtUtility.generateToken(userDetailsImp);
        String role = userDetailsImp.getRole();
        String userName = userDetailsImp.getUsername();
        JWTResponse jwtResponse = new JWTResponse();
        jwtResponse.setJwtToken(token);
        jwtResponse.setRole(role);
        return jwtResponse;

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
