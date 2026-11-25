package com.Sidhu.journalApp.service;

import com.Sidhu.journalApp.Constants.PlaceHolders;
import com.Sidhu.journalApp.apiResponse.WeatherResponse;
import com.Sidhu.journalApp.cache.Appcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherService {

    @Autowired
    RedisService redisService;

    @Value("${weather.api.key}")
    private String apiKey;

//    @Value("${weather_api_key}")
//    private String apiKey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Appcache appcache;

    public WeatherResponse getWeather(String city){
        String cacheKey = "weather of_" + city;
        WeatherResponse weatherResp = redisService.get(cacheKey, WeatherResponse.class);

        if (weatherResp != null) {
            return weatherResp;
        }
        else{
            String weatherApiTemplate = appcache.APPCACHE.get(Appcache.keys.WEATHER_API.toString());
            if (weatherApiTemplate == null || weatherApiTemplate.isBlank()) {
                throw new IllegalStateException("Weather API URL is missing from the application cache");
            }

            String finalAPI = UriComponentsBuilder.fromUriString(weatherApiTemplate
                            .replace(PlaceHolders.API_KEY, apiKey)
                            .replace(PlaceHolders.CITY, "{city}"))
                    .buildAndExpand(city)
                    .encode()
                    .toUriString();
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                    finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body=response.getBody();
            if(body!=null){
                redisService.set(cacheKey, body,300L);
            }

            return body;
        }
    }
}
