package com.example.dataapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.dataapp.R;
import com.example.dataapp.common.AppDialog;
import com.example.dataapp.common.AppGlobal;
import com.example.dataapp.database.DatabaseHelper;
import com.example.dataapp.models.contactList.ContactListItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddContactFragment extends Fragment implements View.OnClickListener {
    TextInputLayout tilACEmail;

    TextInputEditText edACName, edACEmail, edACAddress, edACNumber;
    Button btnACAdd;
    String strName, strEmail, strAddress, strNumber;
    DatabaseHelper databaseHelper;

    public AddContactFragment() {
        // Required empty public constructor
    }

    public static AddContactFragment newInstance() {
        AddContactFragment fragment = new AddContactFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        initViews(view);
        setListeners();
        return view;
    }

    public void initViews(View view) {
        try {
            databaseHelper = new DatabaseHelper(getActivity());

            edACName = view.findViewById(R.id.edACName);
            edACEmail = view.findViewById(R.id.edACEmail);
            edACAddress = view.findViewById(R.id.edACAddress);
            edACNumber = view.findViewById(R.id.edACNumber);
            tilACEmail = view.findViewById(R.id.tilACEmail);
            btnACAdd = view.findViewById(R.id.btnACAdd);/*
            ContactListItem contactListItem = databaseHelper.getNote(3);
            AppGlobal.displayShortToast(getActivity(),""+contactListItem.getStrName());*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListeners() {
        try {
            btnACAdd.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnACAdd:
                try {
                    strName = edACName.getText().toString();
                    strEmail = edACEmail.getText().toString();
                    strAddress = edACAddress.getText().toString();
                    strNumber = edACNumber.getText().toString();

                    if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strAddress) && !TextUtils.isEmpty(strNumber)) {
                        if (AppGlobal.isValidEmail(strEmail)) {
                            if (tilACEmail.isEnabled()) {
                                tilACEmail.setError(null);
                            }
                            ContactListItem contactListItem = new ContactListItem(strName, strEmail, strAddress, strNumber);
                            databaseHelper.insertContact(contactListItem);
                            AppDialog.showAlertDialog(getActivity(), null,
                                    getResources().getString(R.string.add_contact_added_msg), getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            clearEditText();
                                            AppGlobal.hideKeyBoard(getActivity(), edACNumber);
                                            edACName.requestFocus();

                                        }
                                    });
                        } else {
                            tilACEmail.setError(getResources().getString(R.string.add_contact_email_not_valid));
                        }
                    } else {
                        AppDialog.showAlertDialog(getActivity(), null,
                                getResources().getString(R.string.add_contact_empty_fields_msg), getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }

    public void clearEditText() {
        try {
            edACName.setText("");
            edACEmail.setText("");
            edACAddress.setText("");
            edACNumber.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

