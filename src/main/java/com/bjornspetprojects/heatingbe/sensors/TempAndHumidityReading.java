package com.bjornspetprojects.heatingbe.sensors;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TempAndHumidityReading {
    private Long temperature;
    private Long humidity;
}
