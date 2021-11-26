package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Notification;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.NotificationRepo;
import com.bluescripts.globaloffice.office.requests.NoticeRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.NoticeResponse;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;

    private final ModelMapper modelMapper;

    public GenericResponse createNotify(NoticeRequest noticeRequest)
    {
        String NoticationId= IdUtils.generateId(StringConstant.notice_prefix);

        Notification notification=new Notification();
        notification.setNotificationId(NoticationId);
        notification.setUserName(noticeRequest.getUserName());
        notification.setSubject(noticeRequest.getSubject());
        notification.setDate(noticeRequest.getDate());
        notification.setStatus(noticeRequest.getStatus());

        notificationRepo.save(notification);

        GenericResponse response=new GenericResponse();
        response.setMessage("Notification Created Successfully");
        return  response;
    }

    public NoticeResponse getNotification(String notificationId)
    {
        Notification notification= notificationRepo.findByNotificationId(notificationId).orElseThrow(()->
        {
            return new NoRecordFoundException("No Record Found"+notificationId);
        });
        return modelMapper.map(notification,NoticeResponse.class);
    }

    public GenericDeleteResponse deleteNotification(String notificationId)
    {
        Notification notificationdelete = notificationRepo.findByNotificationId(notificationId).orElseThrow(()->
        {
            return new NoRecordFoundException("No Record Found"+notificationId);
        });

        GenericDeleteResponse response= new GenericDeleteResponse(notificationId,true);
        response.setDeleted(true);
        notificationRepo.save(notificationdelete);
        return response;
    }

    public Iterable<Notification> getAllNotifications()
    {
        return notificationRepo.findAll();
    }

}

