package com.mvvm.viewmodel;

import android.os.Bundle;

public interface IViewModel {
    void onCreateViewModel(Bundle bundle);
    void onPauseViewModel();
    void onResumeViewModel();
    void onDestroyViewModel();
}
