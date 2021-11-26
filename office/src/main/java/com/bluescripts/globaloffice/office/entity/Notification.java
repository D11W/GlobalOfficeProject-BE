package com.bluescripts.globaloffice.office.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {

    @Id
    private String notificationId;
    private String userName;
    private String subject;
    private String date;
    private String status;
}
