package com.bjornspetprojects.pitempreader.readtemp;


import com.bjornspetprojects.heatingbe.sensors.TempAndHumidityReading;

public interface ReadTempService {
    TempAndHumidityReading readData() throws Exception;
}
