// Noel Dalmas Oduory S2110899
package com.example.oduory_noel_s2110899.ui.quick_observations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class QuickObservationsViewModel extends ViewModel {

    private final MutableLiveData<List<WeatherObservation>> quickObservations;
    private final List<String> locationIds = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");

    public QuickObservationsViewModel() {
        quickObservations = new MutableLiveData<>();
        for (String locationId : locationIds) {
            fetchData(locationId);
        }
    }

    public LiveData<List<WeatherObservation>> getQuickObservations() {
        return quickObservations;
    }

    public void fetchData(String locationId) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/" + locationId);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder xmlData = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlData.append(line);
                }
                reader.close();
                connection.disconnect();
                parseXML(xmlData.toString(), locationId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void parseXML(String xmlData, String locationId) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlData));

            int eventType = parser.getEventType();
            String date = null;
            String title = null;
            String description = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("pubDate")) {
                        date = parser.nextText();
                    } else if (parser.getName().equals("title")) {
                        title = parser.nextText();
                    } else if (parser.getName().equals("description")) {
                        description = parser.nextText();
                    }
                } else if (eventType == XmlPullParser.END_TAG && parser.getName().equals("item")) {
                    // Use regex to extract data from the title
                    String currentOutlook = extractValue(title, ": (.*?),");
                    String titleTemp = extractValue(title, "(\\d+Â°C \\(\\d+Â°F\\))");
                    String time = extractValue(title, "- (.*?):");

                    // Use regex to search for specific substrings in the description
                    String temperature = extractValue(description, "Temperature: ([^,]+)");
                    String windDirection = extractValue(description, "Wind Direction: ([^,]+)");
                    String windSpeed = extractValue(description, "Wind Speed: ([^,]+)");
                    String humidity = extractValue(description, "Humidity: ([^,]+)");
                    String pressure = extractValue(description, "Pressure: ([^,]+,[^,]+)");
                    String visibility = extractValue(description, "Visibility: ([^,]+)");

                    // Determine whether it's day or night
                    boolean isDay = Integer.parseInt(time.split(":")[0]) >= 6 && Integer.parseInt(time.split(":")[0]) < 18;

                    // Parse the temperature and humidity
                    int temp = Integer.parseInt(temperature.replaceAll("\\D+", ""));
                    int hum = Integer.parseInt(humidity.replace("%", ""));

                    // Set the weather icon based on the temperature, humidity, and time of day
                    String weatherIcon;
                    if (temp <= 10 && hum > 80) {
                        weatherIcon = isDay ? "day_snow" : "night_snow";
                    } else if (temp > 30 && hum > 80) {
                        weatherIcon = isDay ? "day_rain_thunder" : "night_rain_thunder";
                    } else if (hum > 80) {
                        weatherIcon = isDay ? "day_rain" : "night_rain";
                    } else {
                        weatherIcon = isDay ? "day_clear" : "night_clear";
                    }

                    // Create a WeatherObservation object and add it to the list
                    WeatherObservation observation = new WeatherObservation(date, locationId, currentOutlook, titleTemp, temperature, windDirection, windSpeed, humidity, pressure, visibility, weatherIcon);
                    quickObservations.postValue(Arrays.asList(observation));
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractValue(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

}