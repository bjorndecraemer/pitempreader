package com.bjornspetprojects.pitempreader.readtemp;


import com.bjornspetprojects.heatingbe.sensors.TempAndHumidityReading;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Profile("!dev")
public class ReadTempImpl implements ReadTempService{

    private static final String TEMP_REGEX = "temp(\\d+\\.\\d+)";
    private static final String HUMIDITY_REGEX = "hum(\\d+\\.\\d+)";
    private final Pattern patTemp = Pattern.compile(TEMP_REGEX);
    private final Pattern patHum = Pattern.compile(HUMIDITY_REGEX);

    public TempAndHumidityReading readData() throws Exception{
        //System.out.println("START");
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("python /home/pi/Documents/temp.py");

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        //System.out.println("PROCESS");
        String line;
        List<String> foundStrings = new ArrayList<>();

        while((line = stdInput.readLine()) != null){
            //System.out.println("Line : "+line);
            System.out.println(line);
            foundStrings.add(line);
        }
        stdInput.close();
        // read any errors from the attempted command
        String s = null;
        //System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
        stdError.close();

        p.waitFor();
        System.out.println("Done reading");
        if(foundStrings != null && foundStrings.size() == 2){
            return generateTempAndHumidityReading(foundStrings);
        }
        return null;
    }

    private TempAndHumidityReading generateTempAndHumidityReading(List<String> foundStrings) {
        Matcher tempMatcher = patTemp.matcher(foundStrings.get(0));
        Matcher humMatcher = patHum.matcher(foundStrings.get(1));
        Long temp = null;
        Long humidity = null;

        if(tempMatcher.matches()){
            temp = (new BigDecimal(tempMatcher.group(1)).longValue());
        }
        if(humMatcher.matches()){
            humidity = (new BigDecimal(humMatcher.group(1)).longValue());
        }

        return TempAndHumidityReading.builder().temperature(temp).humidity(humidity).build();
    }
}
