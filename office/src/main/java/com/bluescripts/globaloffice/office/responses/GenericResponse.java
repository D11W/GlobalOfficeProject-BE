package com.bluescripts.globaloffice.office.responses;
import lombok.*;

@Data
@NoArgsConstructor
public class GenericResponse {

    private String message;

    public GenericResponse(String message)
    {
        this.message=message;
    }
}
