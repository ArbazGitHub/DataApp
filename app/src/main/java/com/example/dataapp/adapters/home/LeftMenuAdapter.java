package com.example.dataapp.adapters.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dataapp.R;
import com.example.dataapp.models.home.LeftMenuMain;

import java.util.ArrayList;

public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.ViewHolder> {
    ArrayList<LeftMenuMain> leftMenuMainArrayList;
    Context context;
    LeftMenuMain leftMenuMain;


    public LeftMenuAdapter(ArrayList<LeftMenuMain> leftMenuMainArrayList, Context context) {
        this.leftMenuMainArrayList = leftMenuMainArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.left_menu_row, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        leftMenuMain = leftMenuMainArrayList.get(position);
        try {
            holder.tvLeftMenu.setText(leftMenuMain.getStrMenuTitle());
            if (leftMenuMain.getImgId() != 0) {
                holder.ivLeftMenu.setImageResource(leftMenuMain.getImgId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return leftMenuMainArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tvLeftMenu;
        public ImageView ivLeftMenu;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvLeftMenu = view.findViewById(R.id.tvLeftMenu);

        }
    }
}
