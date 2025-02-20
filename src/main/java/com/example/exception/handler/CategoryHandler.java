package com.example.exception.handler;

import com.example.exception.GeneralException;
import com.example.payload.status.BaseStatus;

public class CategoryHandler extends GeneralException {
    public CategoryHandler (BaseStatus status) {
        super(status);
    }
}
