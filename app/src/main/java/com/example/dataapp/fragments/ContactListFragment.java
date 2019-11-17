package com.example.dataapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataapp.R;
import com.example.dataapp.adapters.contactList.ContactListAdapter;
import com.example.dataapp.common.AppConstants;
import com.example.dataapp.common.AppDialog;
import com.example.dataapp.common.AppGlobal;
import com.example.dataapp.database.DatabaseHelper;
import com.example.dataapp.interfaces.AdapterButtonClicks;
import com.example.dataapp.models.contactList.ContactListItem;

import java.util.ArrayList;

public class ContactListFragment extends Fragment implements AdapterButtonClicks {
    RecyclerView rvContactList;
    TextView tvCLEmpty;
    ContactListAdapter contactListAdapter;
    ArrayList<ContactListItem> contactListItemArrayList;
    ContactListItem contactListItem;

    DatabaseHelper databaseHelper;

    public ContactListFragment() {
        // Required empty public constructor
    }

    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        initViews(view);
        return view;
    }

    public void initViews(View view) {
        try {
            databaseHelper = new DatabaseHelper(getActivity());

            rvContactList = view.findViewById(R.id.rvContactList);
            tvCLEmpty = view.findViewById(R.id.tvCLEmpty);
            rvContactList.setLayoutManager(new LinearLayoutManager(getActivity()));
            loadContacts();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adapterButtonClick(int position, String buttonType, final ContactListItem contactListItem) {
        if (contactListItem != null) {
            if (buttonType.equals(AppConstants.CONTACT_BUTTON_TYPE_EDIT)) {

            } else {
                AppDialog.showAlertDialog(getActivity(), null,
                        getResources().getString(R.string.add_contact_confirm_delete_msg), getString(R.string.txt_yes), getString(R.string.txt_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                databaseHelper.deleteContact(contactListItem);
                                loadContacts();
                                dialogInterface.dismiss();
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });


            }
        } else {
            AppGlobal.displayShortToast(getActivity(), getResources().getString(R.string.app_something_wrong_msg));
        }
    }

    public void loadContacts() {
        try {
            contactListItemArrayList = new ArrayList<>();
            if (databaseHelper.getContactCount() > 0) {
                AppGlobal.setEmptyView(false, tvCLEmpty, rvContactList);
                contactListAdapter = new ContactListAdapter(getActivity(), databaseHelper.getContacts(), this);
                rvContactList.setAdapter(contactListAdapter);

            } else {
                AppGlobal.setEmptyView(true, tvCLEmpty, rvContactList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
