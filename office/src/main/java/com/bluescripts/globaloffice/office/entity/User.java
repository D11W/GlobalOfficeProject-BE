package com.bluescripts.globaloffice.office.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    @Column(unique = true)
    private String contactNo;
    private String company_name;
    private String userName;
    private String role;
    private String emailId;
    private String password;
    private boolean vaccine_status;
    private boolean isDelete;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Login login;


}
