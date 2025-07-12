package com.chabiamine.Shortify.dto;

import java.util.Map;

public class LinkAnalyticsDto {

    public int totalClicks ;
    public String firstClick;
    public String lastClick;
    public Map<String , Integer> topCountries ;
    public Map<String , Integer> deviceTypes ;
    public Map<String , Integer> referrers ;

    @Override
    public String toString() {
        return "LinkAnalyticsDto{" +
                "totalClicks=" + totalClicks +
                ", firstClick='" + firstClick + '\'' +
                ", lastClick='" + lastClick + '\'' +
                ", topCountries=" + topCountries +
                ", deviceTypes=" + deviceTypes +
                ", referrers=" + referrers +
                '}';
    }
}
