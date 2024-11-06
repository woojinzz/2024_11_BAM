package com.koreaIT.BAM.util;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String getDateStr() {
        ZonedDateTime now = ZonedDateTime.now();
        // 날짜와 시간을 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter); 
    }
}
