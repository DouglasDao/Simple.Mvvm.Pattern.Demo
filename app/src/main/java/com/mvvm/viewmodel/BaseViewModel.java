package com.mvvm.viewmodel;

import com.mvvm.view.iview.IView;

/**
 * Created by Dell on 12-03-2018.
 */

public abstract class BaseViewModel implements IViewModel {

    private IViewModel iViewModel;
    private IView iView;

    public BaseViewModel(IView iView) {
        this.iView = iView;
        iView.bindViewModel(this);
    }

    @Override
    public void onPauseViewModel() {
    }

    @Override
    public void onResumeViewModel() {

    }

    @Override
    public void onDestroyViewModel() {

    }
}
