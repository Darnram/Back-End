package com.danram.server.service.alarm;

import com.danram.server.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl {

    private final AlarmRepository alarmRepository;


}
