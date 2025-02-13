package com.example.exception.handler;

import com.example.exception.GeneralException;
import com.example.payload.status.BaseStatus;

public class TempHandler extends GeneralException {
    public TempHandler(BaseStatus status) {
        super(status);
    }
}
