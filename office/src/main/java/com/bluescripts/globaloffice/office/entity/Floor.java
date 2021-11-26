package com.bluescripts.globaloffice.office.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Floor {

    @Id
    private String floorId;
    private String company_name;
    private String country;
    private String city;
    private String building;
    private String office;
    private String wing;
    private boolean isDelete;

}
