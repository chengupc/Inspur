package com.inspur.pm.YarnResourceRecord;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.inspur.pm.YarnResourceRecord.Util.YarnUTtill.*;
import static com.inspur.pm.YarnResourceRecord.Util.http2Json.postDownloadJson;

@Slf4j
public class YarnResourceRecordEnter {

    public static void main(String[] args) throws InterruptedException {
        Properties properties = getProperties();
        String getURL = (String) properties.get("getURL");
        log.info("URL1 is {}",getURL);

        while (true){
            startTimer(getURL, properties);
            Thread.sleep(1000*60*60); //sleep 1 hour
        }
    }

    public static void startTimer(String URL1, Properties properties){
                try{
                    String JsonString1 = null;

                    JsonString1 = postDownloadJson(URL1, "123");

                    log.info("JsonString1 : {}",JsonString1);

                    HashMap<String, Integer> map1 = getMap1(JsonString1);
                    log.info("map1 ", map1.toString());

                    HashMap<HashMap<String, Object>, HashMap<String, HashMap<String, Object>>> map2 = getMap2(JsonString1);
                    log.info("map2 ", map2.toString());
                    data2Oracle(map1, map2, properties);
                }
                catch (Exception e){
                    log.info("data2oracle error");
                }
    }
}


