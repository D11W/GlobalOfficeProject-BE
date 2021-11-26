package com.bluescripts.globaloffice.office.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    private String seatId;
    private String floorId;
    private boolean seatAvailable;
    private boolean seatOccupy;
}
