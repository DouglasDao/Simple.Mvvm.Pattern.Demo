package com.mvvm.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.mvvm.adapter.ContactsAdapter;
import com.mvvm.com.R;
import com.mvvm.com.databinding.ActivityLauncherBinding;
import com.mvvm.model.ContactsData;
import com.mvvm.view.iview.LauncherActivityView;
import com.mvvm.viewmodel.LauncherViewModel;

import java.util.List;

public class LauncherActivity extends BaseActivity<ActivityLauncherBinding> implements LauncherActivityView {

    private ActivityLauncherBinding activityDataBinding;
    private LauncherViewModel mLauncherViewModel;
    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLauncherViewModel = new LauncherViewModel(this, this);
        mLauncherViewModel.onCreateViewModel(getIntent().getExtras());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLauncherViewModel.onDestroyViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLauncherViewModel.onResumeViewModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    public void setActivityViewModel(ActivityLauncherBinding activityDataBinding) {
        this.activityDataBinding = activityDataBinding;
        activityDataBinding.setMovies(mLauncherViewModel);
        activityDataBinding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void contactsListSync(List<ContactsData> data) {
        if (mContactsAdapter == null) {
            mContactsAdapter = new ContactsAdapter(data);
            activityDataBinding.rvContacts.setAdapter(mContactsAdapter);
        } else {
            mContactsAdapter.notifyDataSetChanged();
        }
    }
}
