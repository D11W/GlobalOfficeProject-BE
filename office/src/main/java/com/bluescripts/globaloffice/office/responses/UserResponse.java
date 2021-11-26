package com.bluescripts.globaloffice.office.responses;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
public class UserResponse {

    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String contactNo;
    private String company_name;
    private String userName;
    private String role;
//    private String password;
    private boolean vaccine_status;
    private boolean isDeleted;
}
