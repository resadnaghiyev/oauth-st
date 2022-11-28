package com.rashad.sociallogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {

    private Boolean success;
    private Object data;
    private String message;
    private Object error;
}
