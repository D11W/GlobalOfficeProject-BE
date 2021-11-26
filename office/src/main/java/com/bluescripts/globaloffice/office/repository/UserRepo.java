package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,String>
{
    Optional<User> findByUserId(String userId);

}
