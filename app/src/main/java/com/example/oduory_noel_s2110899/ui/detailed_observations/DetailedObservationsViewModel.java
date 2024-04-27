// DetailedObservationsViewModel.java
package com.example.oduory_noel_s2110899.ui.detailed_observations;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class DetailedObservationsViewModel extends ViewModel {

    private final MutableLiveData<List<String>> detailedObservations;
    private final List<String> locationIds = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");

    public DetailedObservationsViewModel() {
        detailedObservations = new MutableLiveData<>();
        for (String locationId : locationIds) {
            fetchData(locationId);
        }
    }

    public LiveData<List<String>> getDetailedObservations() {
        return detailedObservations;
    }

    private void fetchData(String locationId) {
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
                parseXML(xmlData.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void parseXML(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlData));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {
                    String title = parser.nextText();
                    detailedObservations.getValue().add(title);
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}