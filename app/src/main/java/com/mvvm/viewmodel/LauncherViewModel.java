package com.mvvm.viewmodel;


import android.content.Context;
import android.os.Bundle;

import com.mvvm.model.ContactsData;
import com.mvvm.view.iview.LauncherActivityView;
import com.mvvm.webservice.ApiClient;
import com.mvvm.webservice.ApiInterface;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dell on 27-01-2018.
 */

public class LauncherViewModel extends BaseViewModel {

    private Context mContext;
    private Subscription mSubscription;
    private LauncherActivityView launcherActivityView;
    private List<ContactsData> mContactsList;

    public LauncherViewModel(Context mContext, LauncherActivityView launcherActivityView) {
        super(launcherActivityView);
        this.mContext = mContext;
        this.launcherActivityView = launcherActivityView;
    }

    @Override
    public void onCreateViewModel(Bundle bundle) {
        apiCallToGetContacts();
    }

    private void apiCallToGetContacts() {
        if (launcherActivityView.hasNetworkConnection()) {
            launcherActivityView.showProgressbar();
            mSubscription = ApiClient.getClient().create(ApiInterface.class).performListContacts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<ContactsData>>() {
                        @Override
                        public void onCompleted() {
                            launcherActivityView.dismissProgressbar();
                            if (launcherActivityView != null) {
                                launcherActivityView.contactsListSync(mContactsList);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            launcherActivityView.dismissProgressbar();
                        }

                        @Override
                        public void onNext(List<ContactsData> contactsData) {
                            launcherActivityView.dismissProgressbar();
                            if (!contactsData.isEmpty()) {
                                LauncherViewModel.this.mContactsList = contactsData;
                            }
                        }
                    });
        } else {
            launcherActivityView.showNetworkMessage();
        }
    }

    @Override
    public void onDestroyViewModel() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void onResumeViewModel() {
        apiCallToGetContacts();
    }
}
