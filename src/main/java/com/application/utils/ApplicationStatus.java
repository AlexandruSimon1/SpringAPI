package com.application.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationStatus {
    private final String message;
    private final String status;

    @Override
    public String toString() {
        return "Application Status Error " +
                message + " Reason Message Error " + status;
    }
}
