package com.example.dataapp.adapters.categoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dataapp.R;
import com.example.dataapp.models.categoryList.CategoryListItem;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    Context context;
    ArrayList<CategoryListItem> categoryListItemArrayList;
    CategoryListItem categoryListItem;


    public CategoryListAdapter(Context context, ArrayList<CategoryListItem> categoryListItemArrayList) {
        this.context = context;
        this.categoryListItemArrayList = categoryListItemArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        categoryListItem = categoryListItemArrayList.get(position);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return categoryListItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        TextView tvCatName;
        ImageView ivCatLEdit, ivCatLDelete;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvCatName = view.findViewById(R.id.tvCLTitle);
            ivCatLEdit = view.findViewById(R.id.tvCLName);
            ivCatLDelete = view.findViewById(R.id.tvCLAddress);

        }
    }
}
