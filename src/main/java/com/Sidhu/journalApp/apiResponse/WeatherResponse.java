package com.Sidhu.journalApp.apiResponse;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse  {

    private Current current;

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("last_updated_epoch")
        private int lastUpdatedE;

        @JsonProperty("last_updated")
        private String lastUpdated;

        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("temp_f")
        private double tempF;

        @JsonProperty("is_day")
        private int isDay;

        private Condition condition;

        private int humidity;

        private int cloud;
    }

    @Getter
    @Setter
    public static class Condition {

        private String text;

        private String icon;

        private int code;
    }

}
