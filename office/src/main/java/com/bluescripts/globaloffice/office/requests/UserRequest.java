package com.bluescripts.globaloffice.office.requests;

import lombok.*;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String contactNo;
    private String company_name;
    private String userName;
//    private String role;
    private String password;
    private boolean vaccine_status;
    private String emailId;

}
