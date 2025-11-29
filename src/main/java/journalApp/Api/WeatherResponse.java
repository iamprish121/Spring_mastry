package journalApp.Api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
    private Current current;
    @Getter
    @Setter
    public class Current {
        private String observation_time;
        private int weather_code;
        private List<String> weather_descriptions;
        private int feelslike;
    }
}
