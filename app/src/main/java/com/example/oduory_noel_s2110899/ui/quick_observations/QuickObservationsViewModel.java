package com.example.oduory_noel_s2110899.ui.quick_observations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class QuickObservationsViewModel extends ViewModel {

    private final MutableLiveData<String> quickObservationsText;

    public QuickObservationsViewModel() {
        quickObservationsText = new MutableLiveData<>();
        quickObservationsText.setValue(getCurrentDateTime());
    }

    public LiveData<String> getText() {
        return quickObservationsText;
    }

    private String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}