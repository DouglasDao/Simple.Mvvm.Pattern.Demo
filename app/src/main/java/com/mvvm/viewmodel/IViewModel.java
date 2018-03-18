package com.mvvm.viewmodel;

import android.os.Bundle;

/**
 * Created by Dell on 03-03-2018.
 */

public interface IViewModel {
    void onCreateViewModel(Bundle bundle);
    void onPauseViewModel();
    void onResumeViewModel();
    void onDestroyViewModel();
}
