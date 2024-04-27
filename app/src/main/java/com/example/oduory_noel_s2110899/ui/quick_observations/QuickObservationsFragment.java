// QuickObservationsFragment.java
package com.example.oduory_noel_s2110899.ui.quick_observations;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import java.util.List;

public class QuickObservationsFragment extends Fragment {

    private final List<String> locationIds = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");

    private FragmentQuickObservationsBinding binding;
    private TextView currentRainfall;

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

        quickObservationsViewModel.getQuickObservations().observe(getViewLifecycleOwner(), observations -> {
            for (WeatherObservation observation : observations) {
                // Update the views with the fetched weather data
                TextView textDate = binding.textDate;
                textDate.setText(observation.getDate());

                Spinner locationName = binding.locationName;
                locationName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        quickObservationsViewModel.fetchData(locationIds.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                TextView currentTemperature = binding.currentTemperature;
                currentTemperature.setText(observation.getTemperature());

                TextView currentWindSpeed = binding.currentWindSpeed;
                currentWindSpeed.setText(observation.getWindSpeed());

                TextView currentRainfall = binding.currentRainfall;
                currentRainfall.setText(observation.getHumidity());

                TextView showDetailed = binding.showDetailed;
                showDetailed.setOnClickListener(v -> {
                    // Navigate to the fragment_detailed_observations.xml
                    Navigation.findNavController(v).navigate(R.id.action_navigation_quick_observations_to_navigation_detailed_observations);
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