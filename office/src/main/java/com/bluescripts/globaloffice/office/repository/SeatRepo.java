package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepo extends JpaRepository <Seat,String>
{
    Optional<Seat> findBySeatId(String seatId);
}
