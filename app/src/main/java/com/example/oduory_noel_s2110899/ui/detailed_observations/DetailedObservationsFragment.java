package com.example.oduory_noel_s2110899.ui.detailed_observations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.oduory_noel_s2110899.databinding.FragmentDetailedObservationsBinding;

public class DetailedObservationsFragment extends Fragment {

    private FragmentDetailedObservationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DetailedObservationsViewModel detailedObservationsViewModel =
                new ViewModelProvider(this).get(DetailedObservationsViewModel.class);

        binding = FragmentDetailedObservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detailedObservationsViewModel.getText().observe(getViewLifecycleOwner(), text -> {
            TextView textView = binding.regionName;
            textView.setText(text);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}