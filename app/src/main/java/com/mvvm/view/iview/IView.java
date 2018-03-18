package com.mvvm.view.iview;

import com.mvvm.viewmodel.IViewModel;

public interface IView {
    void bindViewModel(IViewModel iViewModel);

    void showProgressbar();

    void dismissProgressbar();

    void showNetworkMessage();

    boolean hasNetworkConnection();
}
