package com.bjornspetprojects.pitempreader;

import com.bjornspetprojects.heatingbe.sensors.TempAndHumidityReading;
import com.bjornspetprojects.pitempreader.broadcasttemp.BroadcastMetrics;
import com.bjornspetprojects.pitempreader.readtemp.ReadTempService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PiTempReader {

    private final ReadTempService readTemp;
    private final BroadcastMetrics broadcastMetrics;

    public PiTempReader(ReadTempService readTemp, BroadcastMetrics broadcastMetrics) {
        this.readTemp = readTemp;
        this.broadcastMetrics = broadcastMetrics;
    }

    @Scheduled(fixedRate = 60000)
    public void run() {
        try {
            TempAndHumidityReading tempAndHumidityReading = readTemp.readData();
            broadcastMetrics.broadcastMetrics(tempAndHumidityReading);
            System.out.println("Reading : "+tempAndHumidityReading);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
