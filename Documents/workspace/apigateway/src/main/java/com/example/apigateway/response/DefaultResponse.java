package com.example.apigateway.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse {

    private int returnCode;
    private String returnMessage;

    public DefaultResponse(int returnCode) {
        this.returnCode = returnCode;
    }
}
