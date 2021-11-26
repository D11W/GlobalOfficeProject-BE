package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<Login,String>
{
    Optional<Login> findByUserName(String userName);

}
