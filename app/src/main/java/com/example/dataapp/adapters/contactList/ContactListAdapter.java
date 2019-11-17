package com.example.dataapp.adapters.contactList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dataapp.R;
import com.example.dataapp.common.AppConstants;
import com.example.dataapp.interfaces.AdapterButtonClicks;
import com.example.dataapp.models.contactList.ContactListItem;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactListItem> contactListItemArrayList;
    ContactListItem contactListItem;
    AdapterButtonClicks adapterButtonClicks;

    public ContactListAdapter(Context context, ArrayList<ContactListItem> contactListItemArrayList, AdapterButtonClicks adapterButtonClicks) {
        this.context = context;
        this.contactListItemArrayList = contactListItemArrayList;
        this.adapterButtonClicks = adapterButtonClicks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        contactListItem = contactListItemArrayList.get(position);
        try {

            holder.tvCLTitle.setText(contactListItem.getStrName().substring(0,1));
            holder.tvCLName.setText(contactListItem.getStrName());
            holder.tvCLAddress.setText(contactListItem.getStrAddress());
            holder.tvCLNumber.setText(contactListItem.getStrNumber());

            //set tag to button
            holder.ivCLEdit.setTag(position);
            holder.ivCLDelete.setTag(position);

            holder.ivCLEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    contactListItem = contactListItemArrayList.get(pos);
                    adapterButtonClicks.adapterButtonClick(pos, AppConstants.CONTACT_BUTTON_TYPE_EDIT, contactListItem);

                }
            });
            holder.ivCLDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    contactListItem = contactListItemArrayList.get(pos);
                    adapterButtonClicks.adapterButtonClick(pos, AppConstants.CONTACT_BUTTON_TYPE_DELETE, contactListItem);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return contactListItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        TextView tvCLTitle, tvCLName, tvCLAddress, tvCLNumber;
        ImageView ivCLEdit, ivCLDelete;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvCLTitle = view.findViewById(R.id.tvCLTitle);
            tvCLName = view.findViewById(R.id.tvCLName);
            tvCLAddress = view.findViewById(R.id.tvCLAddress);
            tvCLNumber = view.findViewById(R.id.tvCLNumber);
            ivCLEdit = view.findViewById(R.id.ivCLEdit);
            ivCLDelete = view.findViewById(R.id.ivCLDelete);

        }
    }
}
