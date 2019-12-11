package com.bjornspetprojects.pitempreader.broadcasttemp;

import com.bjornspetprojects.heatingbe.sensors.TempAndHumidityReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BroadcastMetrics {

    @Autowired
    private RestTemplate restTemplate;

    public void broadcastMetrics(TempAndHumidityReading tempAndHumidityReading) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TempAndHumidityReading> entity = new HttpEntity<TempAndHumidityReading>(tempAndHumidityReading,headers);
        //restTemplate.put("http://192.168.1.30:8080/api/sensors/newreadings",tempAndHumidityReading, Void.class);
        restTemplate.put("http://192.168.1.30:8080/api/sensors/newreadings",entity, Void.class);
        /*
        HttpHeaders headers = new HttpHeaders();

        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON);
        headers.setAccept(list);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");

        HttpEntity<TempAndHumidityReading> requestUpdate = new HttpEntity<>(tempAndHumidityReading);
        restTemplate.exchange("http://192.168.1.30:8080/api/sensors/newreadings", HttpMethod.PUT, requestUpdate, TempAndHumidityReading.class);
        restTemplate.putForObject()


        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.exchange("http://localhost:8080/api/sensors/newreadings", HttpMethod.PUT,  Void.class);
        */



    }
}
