package com.example.oduory_noel_s2110899.ui.forecast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.oduory_noel_s2110899.databinding.Fragment3DayForecastBinding;

public class ForecastFragment extends Fragment {

    private Fragment3DayForecastBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForecastViewModel forecastViewModel =
                new ViewModelProvider(this).get(ForecastViewModel.class);

        binding = Fragment3DayForecastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.regionSpecificName;
        forecastViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}