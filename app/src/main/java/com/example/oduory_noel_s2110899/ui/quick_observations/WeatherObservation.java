package com.example.oduory_noel_s2110899.ui.quick_observations;

public class WeatherObservation {
    private String date;
    private int locationIndex;
    private String temperature;
    private String windSpeed;
    private String humidity;
    private String weatherIcon;

    public WeatherObservation(String date, int locationIndex, String temperature, String windSpeed, String humidity, String weatherIcon) {
        this.date = date;
        this.locationIndex = locationIndex;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.weatherIcon = weatherIcon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}