package com.bluescripts.globaloffice.office.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_login")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Login {


    @Id
    private String userName;

    private String password;

    private String role;

    private String emailId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
    @Column(name = "one_time_password")
    private String oneTimePassword;

    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;

    public boolean isOTPRequired() {
        if (this.getOneTimePassword() == null) {
            return false;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }

        return true;
    }

}
