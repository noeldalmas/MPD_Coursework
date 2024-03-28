// DetailedObservationsViewModel.java
package com.example.oduory_noel_s2110899.ui.detailed_observations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedObservationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<String>> latestObservations;

    public DetailedObservationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mauritius");

        latestObservations = new MutableLiveData<>();
        latestObservations.setValue(new ArrayList<>()); // replace with your actual data
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getLatestObservations() {
        return latestObservations;
    }
}