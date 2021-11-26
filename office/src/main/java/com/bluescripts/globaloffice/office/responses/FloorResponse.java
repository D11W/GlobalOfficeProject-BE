package com.bluescripts.globaloffice.office.responses;
import lombok.*;

@Data
public class FloorResponse {

    private String floorId;
    private String company_name;
    private String country;
    private String city;
    private String building;
    private String office;
    private String wing;
    private boolean isDelete;
}
