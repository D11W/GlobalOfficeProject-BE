package com.bluescripts.globaloffice.office.requests;

import lombok.*;

@Data
public class NoticeRequest {
    private String notificationId;
    private String userName;
    private String subject;
    private String date;
    private String status;
}
