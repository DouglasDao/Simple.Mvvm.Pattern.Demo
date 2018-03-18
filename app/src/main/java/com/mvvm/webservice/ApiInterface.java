package com.mvvm.webservice;

import com.mvvm.model.ContactsData;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    @GET("json/contacts.json")
    Observable<List<ContactsData>> performListContacts();

}
