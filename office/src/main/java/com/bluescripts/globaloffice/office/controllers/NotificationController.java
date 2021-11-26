package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Notification;
import com.bluescripts.globaloffice.office.requests.NoticeRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.NoticeResponse;
import com.bluescripts.globaloffice.office.service.NotificationService;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notificationId")
    public GenericResponse createNotify(@RequestBody NoticeRequest noticeRequest)
    {
      return notificationService.createNotify(noticeRequest);
    }

//    @PutMapping("/notificationId")
//    public GenericResponse updateNotify(@RequestBody NoticeRequest request, @RequestParam String notificationId)
//    {
//        return notificationService.updateNotification(request,notificationId);
//    }

    @GetMapping("/notificationId")
    public NoticeResponse getNotification(@RequestParam String notificationId)
    {
        return notificationService.getNotification(notificationId);
    }

    @DeleteMapping("/notificationId")
    public GenericDeleteResponse deleteNotify(@RequestParam String notificationId)
    {
        return notificationService.deleteNotification(notificationId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public Iterable<Notification> getAllNotifications()
    {
        return notificationService.getAllNotifications();
    }

}
