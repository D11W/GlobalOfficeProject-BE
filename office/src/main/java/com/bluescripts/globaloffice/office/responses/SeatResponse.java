package com.bluescripts.globaloffice.office.responses;

import lombok.*;

@Data
public class SeatResponse {

    private String seatId;
    private String floorId;
    private boolean seatAvailable;
    private boolean seatOccupy;
}
