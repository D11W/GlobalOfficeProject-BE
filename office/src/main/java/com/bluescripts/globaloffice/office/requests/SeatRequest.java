package com.bluescripts.globaloffice.office.requests;

import lombok.*;

@Data
public class SeatRequest {

    private String seatId;
    private String floorId;
    private boolean seatAvailable;
    private boolean seatOccupy;
}
