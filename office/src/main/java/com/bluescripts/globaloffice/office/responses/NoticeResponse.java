package com.bluescripts.globaloffice.office.responses;

import lombok.*;

@Data
public class NoticeResponse {

    private String notificationId;
    private String userName;
    private String subject;
    private String date;
    private String status;
}
