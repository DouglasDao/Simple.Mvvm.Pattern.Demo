package com.mvvm.viewmodel;

import com.mvvm.view.iview.IView;

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
