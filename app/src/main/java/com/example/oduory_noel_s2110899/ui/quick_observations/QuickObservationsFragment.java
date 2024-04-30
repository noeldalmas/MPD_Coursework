// Noel Dalmas Oduory S2110899
package com.example.oduory_noel_s2110899.ui.quick_observations;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.oduory_noel_s2110899.R;
import com.example.oduory_noel_s2110899.databinding.FragmentQuickObservationsBinding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickObservationsFragment extends Fragment {

    private final List<String> locationIds = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");

    private FragmentQuickObservationsBinding binding;
    private TextView currentHumidity;

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
        QuickObservationsViewModel quickObservationsViewModel =
                new ViewModelProvider(this).get(QuickObservationsViewModel.class);

        binding = FragmentQuickObservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get the Spinner and its adapter
        Spinner locationName = binding.locationName;
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) locationName.getAdapter();

        // Create a variable to hold the previously clicked LinearLayout
        final LinearLayout[] previousClicked = {null};

        // Create a flag to check whether the change in the selected item of the Spinner is caused by a click on a LinearLayout
        final boolean[] isLinearLayoutClicked = {false};

        // Create a mapping between the location names and the corresponding LinearLayouts
        Map<String, LinearLayout> locationLinearLayoutMap = new HashMap<>();
        locationLinearLayoutMap.put("Bangladesh", binding.bangladesh);
        locationLinearLayoutMap.put("New York", binding.newYork);
        locationLinearLayoutMap.put("London", binding.london);
        locationLinearLayoutMap.put("Oman", binding.oman);
        locationLinearLayoutMap.put("Mauritius", binding.mauritius);
        locationLinearLayoutMap.put("Glasgow", binding.glasgow);

        // Set OnClickListener for each LinearLayout
        LinearLayout bangladesh = binding.bangladesh;
        bangladesh.setOnClickListener(v -> {
            quickObservationsViewModel.fetchData(locationIds.get(5));
            if (previousClicked[0] != null) {
                previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff); // Change color back to original
            }
            bangladesh.setBackgroundColor(Color.parseColor("#FF0000")); // Change color to red
            isLinearLayoutClicked[0] = true; // Set the flag
            locationName.setSelection(adapter.getPosition("Bangladesh"));
            previousClicked[0] = bangladesh; // Set the clicked LinearLayout as the previous clicked
        });

        LinearLayout newYork = binding.newYork;
        newYork.setOnClickListener(v -> {
            quickObservationsViewModel.fetchData(locationIds.get(2));
            if (previousClicked[0] != null) {
                previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff);
            }
            newYork.setBackgroundColor(Color.parseColor("#FF0000"));
            isLinearLayoutClicked[0] = true; // Set the flag
            locationName.setSelection(adapter.getPosition("New York"));
            previousClicked[0] = newYork;
        });

        LinearLayout london = binding.london;
        london.setOnClickListener(v -> {
            quickObservationsViewModel.fetchData(locationIds.get(1));
            if (previousClicked[0] != null) {
                previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff);
            }
            london.setBackgroundColor(Color.parseColor("#FF0000"));
            isLinearLayoutClicked[0] = true;
            locationName.setSelection(adapter.getPosition("London"));
            previousClicked[0] = london;
        });

        LinearLayout oman = binding.oman;
        oman.setOnClickListener(v -> {
            quickObservationsViewModel.fetchData(locationIds.get(3));
            if (previousClicked[0] != null) {
                previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff);
            }
            oman.setBackgroundColor(Color.parseColor("#FF0000"));
            isLinearLayoutClicked[0] = true;
            locationName.setSelection(adapter.getPosition("Oman"));
            previousClicked[0] = oman;
        });

        LinearLayout mauritius = binding.mauritius;
        mauritius.setOnClickListener(v -> {
            quickObservationsViewModel.fetchData(locationIds.get(4));
            if (previousClicked[0] != null) {
                previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff);
            }
            mauritius.setBackgroundColor(Color.parseColor("#FF0000"));
            isLinearLayoutClicked[0] = true;
            locationName.setSelection(adapter.getPosition("Mauritius"));
            previousClicked[0] = mauritius;
        });

        LinearLayout glasgow = binding.glasgow;
        glasgow.setOnClickListener(v -> {
            quickObservationsViewModel.fetchData(locationIds.get(0));
            if (previousClicked[0] != null) {
                previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff);
            }
            glasgow.setBackgroundColor(Color.parseColor("#FF0000"));
            isLinearLayoutClicked[0] = true;
            locationName.setSelection(adapter.getPosition("Glasgow"));
            previousClicked[0] = glasgow;
        });

        quickObservationsViewModel.getQuickObservations().observe(getViewLifecycleOwner(), observations -> {
            for (WeatherObservation observation : observations) {
                // Update the views with the fetched weather data
                TextView textDate = binding.textDate;
                textDate.setText("Current Outlook: " + observation.getDate());


                // Set OnItemSelectedListener for the Spinner
                locationName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        quickObservationsViewModel.fetchData(locationIds.get(position));
                        if (previousClicked[0] != null && !isLinearLayoutClicked[0]) {
                            previousClicked[0].setBackgroundResource(R.drawable.cr30bff8fe0ff); // Change color back to original
                        }
                        String selectedLocation = (String) parent.getItemAtPosition(position);
                        LinearLayout selectedLinearLayout = locationLinearLayoutMap.get(selectedLocation);
                        if (selectedLinearLayout != null) {
                            selectedLinearLayout.setBackgroundColor(Color.parseColor("#FF0000")); // Change color to red
                            previousClicked[0] = selectedLinearLayout; // Set the clicked LinearLayout as the previous clicked
                        }
                        isLinearLayoutClicked[0] = false; // Reset the flag
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                TextView currentOutlook = binding.currentOutlook;
                currentOutlook.setText(observation.getCurrentOutlook() + " : Noel Oduory S2110899");

                TextView currentTemperature = binding.currentTemperature;
                currentTemperature.setText(observation.getTemperature());

                TextView currentWindDirection = binding.currentWindDirection;
                currentWindDirection.setText("Wind Direction: " + observation.getWindDirection());

                TextView currentWindSpeed = binding.currentWindSpeed;
                currentWindSpeed.setText(observation.getWindSpeed());

                TextView currentRainfall = binding.currentHumidity;
                currentRainfall.setText(observation.getHumidity());

                TextView currentPressure = binding.currentPressure;
                currentPressure.setText("Pressure: " + observation.getPressure());

                TextView currentVisibility = binding.currentVisibility;
                currentVisibility.setText("Visibility: " + observation.getVisibility());

                TextView showForecast = binding.showForecast;
                showForecast.setOnClickListener(v -> {
                    // Navigate to the fragment_3_day_observations.xml
                    Navigation.findNavController(root).navigate(R.id.action_navigation_quick_observations_to_navigation_forecast);

                    // Pass the location ID to the ForecastFragment
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("locationId", observation.getlocationId());
                    editor.apply();


                });

                // Update the weather icon
                ImageView weatherIcon = binding.currentWeatherIcon;
                int weatherIconResId = getResources().getIdentifier(observation.getWeatherIcon(), "drawable", getActivity().getPackageName());
                weatherIcon.setImageResource(weatherIconResId);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}