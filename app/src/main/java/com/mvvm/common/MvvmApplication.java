package com.mvvm.common;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

public class MvvmApplication extends Application {

    public static MvvmApplication getAppContext(Context context) {
        return (MvvmApplication) context.getApplicationContext();
    }


    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}
