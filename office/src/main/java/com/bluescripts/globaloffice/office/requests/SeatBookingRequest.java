package com.bluescripts.globaloffice.office.requests;

import lombok.Data;

@Data
public class SeatBookingRequest {

    private String seatBookingId;
    private String userId;
    private String bookingDate;
    private String bookingType;
    private String time;

}
