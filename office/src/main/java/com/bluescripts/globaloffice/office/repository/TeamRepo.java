package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team,String> {

    Optional<Team> findByTeamId(String teamId);
}
