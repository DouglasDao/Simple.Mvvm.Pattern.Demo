package com.mvvm.view.iview;

import com.mvvm.model.ContactsData;

import java.util.List;

public interface LauncherActivityView extends IView {
    void contactsListSync(List<ContactsData> data);
}
