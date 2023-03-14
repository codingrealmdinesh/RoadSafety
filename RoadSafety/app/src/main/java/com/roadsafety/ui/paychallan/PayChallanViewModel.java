package com.roadsafety.ui.paychallan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayChallanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PayChallanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pay Challan fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}