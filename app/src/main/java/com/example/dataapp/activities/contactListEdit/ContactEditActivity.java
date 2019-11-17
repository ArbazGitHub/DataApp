package com.example.dataapp.activities.contactListEdit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

import com.example.dataapp.R;
import com.example.dataapp.activities.DataAppBaseActivity;
import com.example.dataapp.common.AppDialog;
import com.example.dataapp.common.AppGlobal;
import com.example.dataapp.database.DatabaseHelper;
import com.example.dataapp.models.contactList.ContactListItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ContactEditActivity extends DataAppBaseActivity implements View.OnClickListener {
    ImageView tbIvBack;
    TextInputEditText edECName, edECEmail,
            edECAddress,
            edECNumber;
    TextInputLayout tilECEmail;
    Button btnUpdate, btnCancel;
    String strName, strEmail, strAddress, strNumber;

    DatabaseHelper databaseHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contct_edit);
        initViews();
        setListeners();
    }

    public void initViews() {
        try {
            setToolbar();
            setToolbarTitle(getResources().getString(R.string.edit_contact_title));
            tbIvBack = displayBack();
            edECName = findViewById(R.id.edECName);
            edECEmail = findViewById(R.id.edECEmail);
            edECAddress = findViewById(R.id.edECAddress);
            edECNumber = findViewById(R.id.edECNumber);
            tilECEmail = findViewById(R.id.tilECEmail);
            btnUpdate = findViewById(R.id.btnUpdate);
            btnCancel = findViewById(R.id.btnCancel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setListeners() {
        try {
            tbIvBack.setOnClickListener(this);
            btnUpdate.setOnClickListener(this);
            btnCancel.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnUpdate:
                strName = edECName.getText().toString();
                strEmail = edECEmail.getText().toString();
                strAddress = edECAddress.getText().toString();
                strNumber = edECNumber.getText().toString();

                if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strAddress) && !TextUtils.isEmpty(strNumber)) {
                    if (AppGlobal.isValidEmail(strEmail)) {
                        if (tilECEmail.isEnabled()) {
                            tilECEmail.setError(null);
                        }
                        ContactListItem contactListItem = new ContactListItem(strName, strEmail, strAddress, strNumber);
                        databaseHelper.insertContact(contactListItem);
                        AppDialog.showAlertDialog(this, null,
                                getResources().getString(R.string.edit_contact_update_msg), getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        AppGlobal.hideKeyBoard(ContactEditActivity.this, edECNumber);

                                    }
                                });
                    } else {
                        tilECEmail.setError(getResources().getString(R.string.add_contact_email_not_valid));
                    }
                } else {
                    AppDialog.showAlertDialog(this, null,
                            getResources().getString(R.string.add_contact_empty_fields_msg), getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                }
                break;
            case R.id.btnCancel:
                onBackPressed();
                break;
            case R.id.tbIvBack:
                onBackPressed();

                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
