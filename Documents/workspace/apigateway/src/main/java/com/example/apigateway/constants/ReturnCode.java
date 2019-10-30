package com.example.apigateway.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ReturnCode {

    AUTH_FAIL(1, "Do not have Permisson"),
    AUTH_SERVER_ACCESS_FAIL(2, "Cannot access Auth Server"),
    SUCCESS(200, "success");

    private int returnCode;
    private String returnMessage;

    ReturnCode(int returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    ReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
