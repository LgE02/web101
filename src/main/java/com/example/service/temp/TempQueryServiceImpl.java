package com.example.service.temp;

import com.example.exception.handler.TempHandler;
import com.example.payload.status.ErrorStatus;
import org.springframework.stereotype.Service;

@Service
public class TempQueryServiceImpl implements TempQueryService {

    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
