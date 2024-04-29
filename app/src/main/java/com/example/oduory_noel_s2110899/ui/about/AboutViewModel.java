// Noel Dalmas Oduory S2110899
package com.example.oduory_noel_s2110899.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("About The App");
    }

    public LiveData<String> getText() {
        return mText;
    }
}