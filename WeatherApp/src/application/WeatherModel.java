package application;

public class WeatherModel {
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String description;

    // Constructor
    public WeatherModel(double temperature, int humidity, double windSpeed, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    // Getters and Setters
    public double getTemperature() { return temperature; }
    public int getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public String getDescription() { return description; }
}
