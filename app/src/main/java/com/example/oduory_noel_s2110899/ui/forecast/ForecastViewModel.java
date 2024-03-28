package com.example.oduory_noel_s2110899.ui.forecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForecastViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ForecastViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Forecast fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}