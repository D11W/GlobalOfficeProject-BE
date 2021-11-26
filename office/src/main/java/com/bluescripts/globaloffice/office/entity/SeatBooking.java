package com.bluescripts.globaloffice.office.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class SeatBooking {

    @Id
    private String seatBookingId;
    private String userId;
    private String bookingDate;
    private String time;
    private String bookingType;

}
