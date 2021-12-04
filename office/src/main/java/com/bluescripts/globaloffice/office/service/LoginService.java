package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.entity.Login;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.LoginRepo;
import com.bluescripts.globaloffice.office.requests.LoginRequest;
import com.bluescripts.globaloffice.office.requests.PasswordresetRequest;
import com.bluescripts.globaloffice.office.responses.CreateLoginResponse;
import com.bluescripts.globaloffice.office.responses.LoginResponse;
import com.bluescripts.globaloffice.office.responses.LoginresetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final LoginRepo loginRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public CreateLoginResponse createLogin(LoginRequest request) {
        Login login = new Login();
        login.setUserName(request.getUserName());
        login.setPassword(request.getPassword());
//       login.setRole(request.getRole());
        loginRepo.save(login);
        CreateLoginResponse loginResponse = new CreateLoginResponse();
        loginResponse.setMessage("login created successfully");
        return loginResponse;
    }

    public LoginResponse getLogin(String userName) {
        Login login = loginRepo.findByUserName(userName).orElseThrow(() -> {
            log.error("login userName not found :{}", userName);
            return new NoRecordFoundException("login user is not found" + userName);
        });
        return modelMapper.map(login, LoginResponse.class);
    }

    public LoginResponse updateLogin(LoginRequest request, String userName) {
        Login login = loginRepo.findByUserName(userName).orElseThrow(() -> {
            log.error("login userName not found :{}", userName);
            return new NoRecordFoundException("login user is not found" + userName);
        });
        login.setUserName(request.getUserName());
        login.setPassword(request.getPassword());
        loginRepo.save(login);
        return modelMapper.map(login, LoginResponse.class);
    }

    public void deleteLogin(String userName) {
        Login login = loginRepo.findByUserName(userName).orElseThrow(() ->
        {
            log.error("delete the user" + userName);
            return new NoRecordFoundException("deleted user" + userName);
        });
        loginRepo.delete(login);
    }

    public LoginresetResponse resetPassword(PasswordresetRequest resetrequest, String emailId) {
        Login login = loginRepo.findByEmailId(emailId).orElseThrow(() ->
        {
            log.error(("Please enter correct Password"));
            return new NoRecordFoundException("Incorrect Password" + emailId);
        });

        String newPassword = resetrequest.getNewpassword();
        login.setPassword(passwordEncoder.encode(resetrequest.getNewpassword()));
        loginRepo.save(login);

        LoginresetResponse resetresponse = new LoginresetResponse();
        resetresponse.setResponsemessage("Password updated succesfully");
        return resetresponse;
    }

    public void sendOTPOnEmail(Login login, String OTP) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("dikzalte119@gmail.com", "globalOffice");
        mimeMessageHelper.setTo(login.getEmailId());

        String subject = "Your OTP will be expired within 5minutes";

        String content = "<p>Hello " + login.getUserName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to change password:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        mimeMessageHelper.setSubject(subject);

        mimeMessageHelper.setText(content, true);
        javaMailSender.send(mimeMessage);
        clearOTP(login);
    }

    public void clearOTP(Login login) {
        login.setOneTimePassword(null);
        login.setOtpRequestedTime(null);
        loginRepo.save(login);
    }


    public String generateOtp(String emailId) throws UnsupportedEncodingException, MessagingException {
        Login login = loginRepo.findByEmailId(emailId).orElseThrow(() -> {
            return new NoRecordFoundException("Incorrect EmailId" + emailId);
        });

        String OTP = RandomString.make(8);
        String encodeOtp = passwordEncoder.encode(OTP);

        login.setOneTimePassword(encodeOtp);
        login.setOtpRequestedTime(new Date());
        loginRepo.save(login);
        sendOTPOnEmail(login, OTP);
        return OTP;
    }
}