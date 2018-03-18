package com.mvvm.viewmodel;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.mvvm.com.databinding.ActivityLauncherBinding;
import com.mvvm.model.ContactsData;
import com.mvvm.view.iview.LauncherActivityView;
import com.mvvm.webservice.ApiClient;
import com.mvvm.webservice.ApiInterface;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LauncherViewModel extends BaseViewModel {

    private Context mContext;
    private Subscription mSubscription;
    private ActivityLauncherBinding activityDataBinding;

    private LauncherActivityView launcherActivityView;
    private List<ContactsData> mContactsList;

    public LauncherViewModel(Context mContext, LauncherActivityView launcherActivityView, ActivityLauncherBinding activityDataBinding) {
        super(launcherActivityView);
        this.mContext = mContext;
        this.launcherActivityView = launcherActivityView;
        this.activityDataBinding = activityDataBinding;
    }

    @Override
    public void onCreateViewModel(Bundle bundle) {
        apiCallToGetContacts();
    }

    private void apiCallToGetContacts() {
        if (launcherActivityView.hasNetworkConnection()) {

            //launcherActivityView.showProgressbar();
            activityDataBinding.shimmerViewContainer.startShimmer();
            activityDataBinding.shimmerViewContainer.setVisibility(View.VISIBLE);
            mSubscription = ApiClient.getClient().create(ApiInterface.class).performListContacts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<ContactsData>>() {
                        @Override
                        public void onCompleted() {
                            activityDataBinding.shimmerViewContainer.stopShimmer();
                            activityDataBinding.shimmerViewContainer.setVisibility(View.GONE);
                            //launcherActivityView.dismissProgressbar();
                            if (launcherActivityView != null) {
                                launcherActivityView.contactsListSync(mContactsList);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            //launcherActivityView.dismissProgressbar();
                            activityDataBinding.shimmerViewContainer.stopShimmer();
                            activityDataBinding.shimmerViewContainer.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(List<ContactsData> contactsData) {
                            //launcherActivityView.dismissProgressbar();
                            activityDataBinding.shimmerViewContainer.stopShimmer();
                            activityDataBinding.shimmerViewContainer.setVisibility(View.GONE);
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
