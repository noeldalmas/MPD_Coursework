package com.example.oduory_noel_s2110899.ui.quick_observations;

public class WeatherObservation {
    private String currentOutlook;
    private String titleTemp;
    private String date;
    private String locationId;
    private String temperature;
    private String windDirection;
    private String windSpeed;
    private String humidity;
    private String pressure;
    private String visibility;
    private String weatherIcon;

    public WeatherObservation(String currentOutlook, String titleTemp, String date, String locationId, String temperature, String windDirection, String windSpeed, String humidity, String pressure, String visibility, String weatherIcon) {
        this.currentOutlook = currentOutlook;
        this.titleTemp = String.valueOf(titleTemp);
        this.date = date;
        this.locationId = locationId;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.weatherIcon = weatherIcon;
    }

    public String getCurrentOutlook() {
        return currentOutlook;
    }

    public void setCurrentOutlook(String currentOutlook) {
        this.currentOutlook = currentOutlook;
    }

    public String getTitleTemp() {
        return titleTemp;
    }

    public void setTitleTemp(String titleTemp) {
        this.titleTemp = titleTemp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getlocationId() {
        return locationId;
    }

    public void setlocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
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

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}