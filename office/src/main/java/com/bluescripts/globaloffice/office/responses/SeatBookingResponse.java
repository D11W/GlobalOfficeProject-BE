package com.bluescripts.globaloffice.office.responses;

import lombok.*;

@Data
public class SeatBookingResponse {

    private String seatBookingId;
    private String userId;
    private String bookingDate;
    private String bookingType;
    private String time;

}
