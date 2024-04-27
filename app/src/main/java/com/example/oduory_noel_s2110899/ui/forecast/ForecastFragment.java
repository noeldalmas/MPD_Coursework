// ForecastFragment.java
package com.example.oduory_noel_s2110899.ui.forecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.oduory_noel_s2110899.R;
import com.example.oduory_noel_s2110899.databinding.Fragment3DayForecastBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForecastFragment extends Fragment {

    private Fragment3DayForecastBinding binding;
    private ForecastViewModel forecastViewModel;
    private Map<String, String> locationIdMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forecastViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(ForecastViewModel.class);

        binding = Fragment3DayForecastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        locationIdMap = new HashMap<>();
        locationIdMap.put("Glasgow", "2648579");
        locationIdMap.put("London", "2643743");
        locationIdMap.put("New York", "5128581");
        locationIdMap.put("Oman", "287286");
        locationIdMap.put("Mauritius", "934154");
        locationIdMap.put("Bangladesh", "1185241");

        final Spinner spinner = binding.regionSpecificName;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.campuses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocation = (String) parent.getItemAtPosition(position);
                String locationId = locationIdMap.get(selectedLocation);
                updateUI(locationId);

                // Update the selectedLocation TextView
                binding.selectedLocation.setText(selectedLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return root;
    }

    private void updateUI(String locationId) {
        forecastViewModel.getForecasts().observe(getViewLifecycleOwner(), new Observer<List<WeatherForecast>>() {
            @Override
            public void onChanged(List<WeatherForecast> weatherForecasts) {
                List<WeatherForecast> selectedLocationForecasts = new ArrayList<>();
                for (WeatherForecast forecast : weatherForecasts) {
                    if (forecast.getLocationId().equals(locationId)) {
                        selectedLocationForecasts.add(forecast);
                    }
                }
                if (selectedLocationForecasts.size() >= 3) {
                    updateDay1UI(selectedLocationForecasts.get(0));
                    updateDay2UI(selectedLocationForecasts.get(1));
                    updateDay3UI(selectedLocationForecasts.get(2));

                    // Set the update time
                    binding.updateTime.setText("Last updated: " + selectedLocationForecasts.get(0).getDate());
                }
            }
        });
    }

    private void updateDay1UI(WeatherForecast forecast) {
        binding.day1ForecastName.setText(forecast.getWeekday());
        binding.weekday1.setText(forecast.getWeekday());
        binding.day1Outlook.setText(forecast.getWeatherOutlook());
        binding.day1LowTemp.setText(forecast.getMinTemperature());
        binding.day1HighTemp.setText(forecast.getMaxTemperature());
        binding.day1AvgTemp.setText(forecast.getMinTemperature());
        binding.day1Direction.setText(forecast.getWindDirection());
        binding.day1Speed.setText(forecast.getWindSpeed());
        binding.day1Visibility.setText(forecast.getVisibility());
        binding.day1Pressure.setText(forecast.getPressure());
        binding.day1Humidity.setText(forecast.getHumidity());
        binding.day1UV.setText(forecast.getUvRisk());
        binding.day1Pollution.setText(forecast.getPollution());
        binding.day1Sunrise.setText(forecast.getSunrise());
        binding.day1Sunset.setText(forecast.getSunset());
    }

    private void updateDay2UI(WeatherForecast forecast) {
        binding.day2ForecastName.setText(forecast.getWeekday());
        binding.weekday2.setText(forecast.getWeekday());
        binding.day2Outlook.setText(forecast.getWeatherOutlook());
        binding.day2LowTemp.setText(forecast.getMinTemperature());
        binding.day2HighTemp.setText(forecast.getMaxTemperature());
        binding.day2AvgTemp.setText(forecast.getMinTemperature());
        binding.day2Direction.setText(forecast.getWindDirection());
        binding.day2Speed.setText(forecast.getWindSpeed());
        binding.day2Visibility.setText(forecast.getVisibility());
        binding.day2Pressure.setText(forecast.getPressure());
        binding.day2Humidity.setText(forecast.getHumidity());
        binding.day2UV.setText(forecast.getUvRisk());
        binding.day2Pollution.setText(forecast.getPollution());
        binding.day2Sunrise.setText(forecast.getSunrise());
        binding.day2Sunset.setText(forecast.getSunset());
    }

    private void updateDay3UI(WeatherForecast forecast) {
        binding.day3ForecastName.setText(forecast.getWeekday());
        binding.weekday3.setText(forecast.getWeekday());
        binding.day3Outlook.setText(forecast.getWeatherOutlook());
        binding.day3LowTemp.setText(forecast.getMinTemperature());
        binding.day3HighTemp.setText(forecast.getMaxTemperature());
        binding.day3AvgTemp.setText(forecast.getMinTemperature());
        binding.day3Direction.setText(forecast.getWindDirection());
        binding.day3Speed.setText(forecast.getWindSpeed());
        binding.day3Visibility.setText(forecast.getVisibility());
        binding.day3Pressure.setText(forecast.getPressure());
        binding.day3Humidity.setText(forecast.getHumidity());
        binding.day3UV.setText(forecast.getUvRisk());
        binding.day3Pollution.setText(forecast.getPollution());
        binding.day3Sunrise.setText(forecast.getSunrise());
        binding.day3Sunset.setText(forecast.getSunset());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}