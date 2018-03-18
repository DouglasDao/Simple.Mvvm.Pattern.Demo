package com.mvvm.adapter.viewholder;

import com.mvvm.com.databinding.InflateContactsBinding;
import com.mvvm.model.ContactsData;
import com.mvvm.viewmodel.ContactsViewModel;

/**
 * Created by Dell on 15-03-2018.
 */

public class ContactsViewHolder extends BaseViewHolder<ContactsData, ContactsViewModel> {

    private InflateContactsBinding contactsBinding;

    public ContactsViewHolder(InflateContactsBinding contactsBinding) {
        super(contactsBinding.cardView);
        this.contactsBinding = contactsBinding;
    }

    @Override
    void populateData() {
        if (contactsBinding.getContacts() == null) {
            contactsBinding.setContacts(new ContactsViewModel(itemView.getContext(), data));
        } else {
            contactsBinding.getContacts().notifyContactsList(data);
        }
    }
}
