package journalApp.Service;

import journalApp.Api.WeatherResponse;
import journalApp.Entity.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final String api_key = "Enter-Your-key-here";

    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse  getWeather(String city){
        String finalApi = API.replace("CITY",city).replace("API_KEY",api_key);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

//    public WeatherResponse  postWeather(String city){
//        String finalApi = API.replace("CITY",city).replace("API_KEY",api_key);
//
//        String requestBody = "{\n" +
//                "    \"userName\":\"test\",\n" +
//                "    \"password\":\"test\"\n" +
//                "}";
//        UserEntry user = UserEntry.builder().userName("username").password("password").build();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key","value");
////        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
////        HttpEntity<UserEntry>  httpEntity = new HttpEntity<>(user);
//        HttpEntity<UserEntry> httpEntity = new HttpEntity<>(user,httpHeaders);
//
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//    }
}
