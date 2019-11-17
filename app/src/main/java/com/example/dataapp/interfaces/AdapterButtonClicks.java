package com.example.dataapp.interfaces;

import com.example.dataapp.models.contactList.ContactListItem;

public interface AdapterButtonClicks {
    void adapterButtonClick(int position, String buttonType, ContactListItem contactListItem);
}
