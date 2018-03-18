package com.mvvm.view.iview;

import com.mvvm.viewmodel.IViewModel;

/**
 * Created by Dell on 16-03-2018.
 */

public interface IView {
    void bindViewModel(IViewModel iViewModel);

    void showProgressbar();

    void dismissProgressbar();

    void showNetworkMessage();

    boolean hasNetworkConnection();
}
