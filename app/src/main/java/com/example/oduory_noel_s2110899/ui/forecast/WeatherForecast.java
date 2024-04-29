// Noel Dalmas Oduory S2110899
package com.example.oduory_noel_s2110899.ui.forecast;

public class WeatherForecast {
    private String locationId;
    private String date;
    private String weekday;
    private String weatherOutlook;
    private String minTemperature;
    private String maxTemperature;
    private String windDirection;
    private String windSpeed;
    private String visibility;
    private String pressure;
    private String humidity;
    private String uvRisk;
    private String pollution;
    private String sunrise;
    private String sunset;

    public WeatherForecast(String locationId, String date, String weekday, String weatherOutlook, String minTemperature, String maxTemperature, String windDirection, String windSpeed, String visibility, String pressure, String humidity, String uvRisk, String pollution, String sunrise, String sunset) {
        this.locationId = locationId;
        this.date = date;
        this.weekday = weekday;
        this.weatherOutlook = weatherOutlook;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.pressure = pressure;
        this.humidity = humidity;
        this.uvRisk = uvRisk;
        this.pollution = pollution;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeatherOutlook() {
        return weatherOutlook;
    }

    public void setWeatherOutlook(String weatherOutlook) {
        this.weatherOutlook = weatherOutlook;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getUvRisk() {
        return uvRisk;
    }

    public void setUvRisk(String uvRisk) {
        this.uvRisk = uvRisk;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}