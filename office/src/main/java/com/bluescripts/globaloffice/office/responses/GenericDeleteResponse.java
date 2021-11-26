package com.bluescripts.globaloffice.office.responses;

import lombok.*;

@AllArgsConstructor
@Data
public class GenericDeleteResponse {

    private String id;
    private boolean deleted;
}
