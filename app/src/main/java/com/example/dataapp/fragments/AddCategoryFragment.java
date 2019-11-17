package com.example.dataapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataapp.R;
import com.example.dataapp.adapters.categoryList.CategoryListAdapter;
import com.example.dataapp.common.AppGlobal;
import com.example.dataapp.models.categoryList.CategoryListItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddCategoryFragment extends Fragment implements View.OnClickListener {
    RecyclerView rvCategoryList;
    TextView tvCatLEmpty;
    TextInputLayout tilACatName;
    TextInputEditText edACatName;
    Button btnACatAdd;
    CategoryListAdapter categoryListAdapter;
    ArrayList<CategoryListItem> categoryListItemArrayList;
    CategoryListItem categoryListItem;

    String strCatName;


    public AddCategoryFragment() {
        // Required empty public constructor
    }

    public static AddCategoryFragment newInstance() {
        AddCategoryFragment fragment = new AddCategoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);

        initViews(view);
        setListeners();
        return view;
    }

    public void initViews(View view) {
        try {
            rvCategoryList = view.findViewById(R.id.rvCategoryList);
            tvCatLEmpty = view.findViewById(R.id.tvCatLEmpty);
            tilACatName = view.findViewById(R.id.tilACatName);
            edACatName = view.findViewById(R.id.edACatName);
            btnACatAdd = view.findViewById(R.id.btnACatAdd);

            rvCategoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
            categoryListItemArrayList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                categoryListItem = new CategoryListItem();
                categoryListItemArrayList.add(categoryListItem);
            }
            categoryListItemArrayList = new ArrayList<>();
            if (categoryListItemArrayList.size() > 0) {
                AppGlobal.setEmptyView(false, tvCatLEmpty, rvCategoryList);
                categoryListAdapter = new CategoryListAdapter(getActivity(), categoryListItemArrayList);
                rvCategoryList.setAdapter(categoryListAdapter);
            } else {
                AppGlobal.setEmptyView(true, tvCatLEmpty, rvCategoryList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListeners() {
        try {
            btnACatAdd.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnACatAdd:
                strCatName = edACatName.getText().toString();
                if (!TextUtils.isEmpty(strCatName)) {
                    if (tilACatName.isEnabled()) {
                        tilACatName.setError(null);

                    }

                } else {
                    tilACatName.setError(getResources().getString(R.string.add_category_cat_name_required));
                }
                break;
            default:
                break;
        }

    }
}
