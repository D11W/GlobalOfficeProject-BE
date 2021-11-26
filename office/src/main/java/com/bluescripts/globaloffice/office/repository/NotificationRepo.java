package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepo extends JpaRepository<Notification,String> {

    Optional<Notification> findByNotificationId(String notificationId);
}
