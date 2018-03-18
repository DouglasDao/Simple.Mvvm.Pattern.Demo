package com.mvvm.view.iview;

import com.mvvm.model.ContactsData;

import java.util.List;

/**
 * Created by Dell on 16-03-2018.
 */

public interface LauncherActivityView extends IView {
    void contactsListSync(List<ContactsData> data);
}
