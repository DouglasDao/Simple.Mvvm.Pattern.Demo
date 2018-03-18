package com.mvvm.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvvm.adapter.viewholder.ContactsViewHolder;
import com.mvvm.com.R;
import com.mvvm.com.databinding.InflateContactsBinding;
import com.mvvm.model.ContactsData;

import java.util.List;


public class ContactsAdapter extends BaseRecyclerAdapter<ContactsData, ContactsViewHolder> {

    private InflateContactsBinding contactsBinding;

    public ContactsAdapter(List<ContactsData> data) {
        super(data);
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        contactsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.inflate_contacts, parent, false);
        return new ContactsViewHolder(contactsBinding);
    }
}
