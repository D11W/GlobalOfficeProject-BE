package com.bluescripts.globaloffice.office.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {

    @Id
    private String teamId;
    private String floorId;
    private String teamName;
}
