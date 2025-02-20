package com.example.exception.handler;

import com.example.exception.GeneralException;
import com.example.payload.status.BaseStatus;

public class StoreHandler extends GeneralException {
    public StoreHandler (BaseStatus status) {
        super(status);
    }
}
