package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepo extends JpaRepository<Floor,String> {

    Optional<Floor> findByFloorId(String floorId);

}
