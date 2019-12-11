package com.bjornspetprojects.pitempreader.readtemp;


import com.bjornspetprojects.heatingbe.sensors.TempAndHumidityReading;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class ReadTempDevImpl implements ReadTempService {
    @Override
    public TempAndHumidityReading readData() throws Exception {
        return TempAndHumidityReading.builder().temperature(12l).humidity(50l).build();
    }
}
