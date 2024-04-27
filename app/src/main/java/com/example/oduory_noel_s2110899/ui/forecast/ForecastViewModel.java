// ForecastViewModel.java
package com.example.oduory_noel_s2110899.ui.forecast;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class ForecastViewModel extends AndroidViewModel {

    private final MutableLiveData<List<WeatherForecast>> forecasts;
    private final List<String> locationIds = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");
    private final Handler handler = new Handler(Looper.getMainLooper());

    public ForecastViewModel(Application application) {
        super(application);
        forecasts = new MutableLiveData<>(new ArrayList<>());
        for (String locationId : locationIds) {
            fetchData(locationId);
        }
    }

    public LiveData<List<WeatherForecast>> getForecasts() {
        return forecasts;
    }

    public void fetchData(String locationId) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/" + locationId);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder xmlData = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlData.append(line);
                }
                reader.close();
                connection.disconnect();
                List<WeatherForecast> newForecasts = parseXML(xmlData.toString(), locationId);
                List<WeatherForecast> currentForecasts = forecasts.getValue();
                if (currentForecasts != null) {
                    currentForecasts.addAll(newForecasts);
                    forecasts.postValue(currentForecasts);
                } else {
                    forecasts.postValue(newForecasts);
                }
            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> Toast.makeText(getApplication(), "Error fetching data: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }

    private List<WeatherForecast> parseXML(String xmlData, String locationId) {
        List<WeatherForecast> forecastList = new ArrayList<>();
        try {
            Log.d("ForecastViewModel", "XML data: " + xmlData); // Log the XML data

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlData));

            int eventType = parser.getEventType();
            String date = null;
            String description = null;
            String title = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("pubDate")) {
                        date = parser.nextText();
                    } else if (parser.getName().equals("description")) {
                        description = parser.nextText();
                    } else if (parser.getName().equals("title")) {
                        title = parser.nextText();
                    }
                } else if (eventType == XmlPullParser.END_TAG && parser.getName().equals("item")) {

                    // Extract the weekday and weather outlook from the title
                    assert title != null;
                    String weekday = title.split(":")[0].trim();
                    String weatherOutlook = title.split(",")[0].split(":")[1].trim();

                    // Parse the description to get the weather data
                    assert description != null;
                    String[] weatherData = description.split(", ");
                    String maxTemperature = weatherData.length > 0 ? weatherData[0].split(": ")[1] : null;
                    String minTemperature = weatherData.length > 1 ? weatherData[1].split(": ")[1] : null;
                    String windDirection = weatherData.length > 2 ? weatherData[2].split(": ")[1] : null;
                    String windSpeed = weatherData.length > 3 ? weatherData[3].split(": ")[1] : null;
                    String visibility = weatherData.length > 4 ? weatherData[4].split(": ")[1] : null;
                    String pressure = weatherData.length > 5 ? weatherData[5].split(": ")[1] : null;
                    String humidity = weatherData.length > 6 ? weatherData[6].split(": ")[1] : null;
                    String uvRisk = weatherData.length > 7 ? weatherData[7].split(": ")[1] : null;
                    String pollution = weatherData.length > 8 ? weatherData[8].split(": ")[1] : null;
                    String sunrise = weatherData.length > 9 ? weatherData[9].split(": ")[1] : null;
                    String sunset = weatherData.length > 10 ? weatherData[10].split(": ")[1] : null;

                    // Create a WeatherForecast object and add it to the list
                    WeatherForecast forecast = new WeatherForecast(locationId, date, weekday, weatherOutlook, minTemperature, maxTemperature, windDirection, windSpeed, visibility, pressure, humidity, uvRisk, pollution, sunrise, sunset);
                    forecastList.add(forecast);
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            handler.post(() -> Toast.makeText(getApplication(), "Error parsing XML: " + e.getMessage(), Toast.LENGTH_LONG).show());
        }
        return forecastList;
    }
}