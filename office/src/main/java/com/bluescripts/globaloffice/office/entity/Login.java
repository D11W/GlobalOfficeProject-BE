package com.bluescripts.globaloffice.office.entity;

import lombok.*;
import javax.persistence.*;

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
}
