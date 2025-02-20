package com.example.exception.handler;

import com.example.exception.GeneralException;
import com.example.payload.status.BaseStatus;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseStatus status) {
        super(status);
    }
}
