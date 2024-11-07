package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONObject;

public class WeatherController {
    @FXML private TextField locationInput;
    @FXML private Label tempLabel;
    @FXML private Label humidityLabel;
    @FXML private Label windSpeedLabel;
    @FXML private Label conditionLabel;
    @FXML private ImageView weatherIcon;

    public void handleGetWeather() {
        String location = locationInput.getText();
        try {
            JSONObject weatherData = WeatherService.getWeatherData(location);
            WeatherModel weather = parseWeatherData(weatherData);
            displayWeather(weather);
        } catch (Exception e) {
            tempLabel.setText("Error fetching data.");
        }
    }

    private WeatherModel parseWeatherData(JSONObject data) {
        double temp = data.getJSONObject("main").getDouble("temp") - 273.15; // Kelvin to Celsius
        int humidity = data.getJSONObject("main").getInt("humidity");
        double windSpeed = data.getJSONObject("wind").getDouble("speed");
        String description = data.getJSONArray("weather").getJSONObject(0).getString("description");

        return new WeatherModel(temp, humidity, windSpeed, description);
    }

    private void displayWeather(WeatherModel weather) {
        tempLabel.setText("Temperature: " + String.format("%.2f", weather.getTemperature()) + "°C");
        humidityLabel.setText("Humidity: " + weather.getHumidity() + "%");
        windSpeedLabel.setText("Wind Speed: " + weather.getWindSpeed() + " m/s");
        conditionLabel.setText("Conditions: " + weather.getDescription());

        // Set icon based on description
        String icon = getWeatherIcon(weather.getDescription());
        weatherIcon.setImage(new Image(getClass().getResourceAsStream("/resources/icons/" + icon + ".png")));
    }

    private String getWeatherIcon(String description) {
        // Logic to return icon filename based on weather description
        if (description.contains("cloud")) return "cloudy";
        if (description.contains("rain")) return "rainy";
        return "sunny";
    }
}
