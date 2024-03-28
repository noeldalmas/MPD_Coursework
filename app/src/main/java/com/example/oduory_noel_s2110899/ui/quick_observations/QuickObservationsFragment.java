package com.example.oduory_noel_s2110899.ui.quick_observations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.oduory_noel_s2110899.databinding.FragmentQuickObservationsBinding;

public class QuickObservationsFragment extends Fragment {

    private FragmentQuickObservationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QuickObservationsViewModel quickObservationsViewModel =
                new ViewModelProvider(this).get(QuickObservationsViewModel.class);

        binding = FragmentQuickObservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDate;
        quickObservationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}