// DetailedObservationsFragment.java
package com.example.oduory_noel_s2110899.ui.detailed_observations;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.oduory_noel_s2110899.databinding.FragmentDetailedObservationsBinding;

public class DetailedObservationsFragment extends Fragment {

    private FragmentDetailedObservationsBinding binding;

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
        DetailedObservationsViewModel detailedObservationsViewModel =
                new ViewModelProvider(this).get(DetailedObservationsViewModel.class);

        binding = FragmentDetailedObservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detailedObservationsViewModel.getDetailedObservations().observe(getViewLifecycleOwner(), observations -> {
            TextView textView = binding.regionName;
            textView.setText(observations.toString());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}