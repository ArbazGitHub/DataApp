package com.example.dataapp.models.categoryList;

import java.io.Serializable;

public class CategoryListItem implements Serializable {
    String strCatName;

    public CategoryListItem() {
    }

    public CategoryListItem(String strCatName) {
        this.strCatName = strCatName;
    }

    public String getStrCatName() {
        return strCatName;
    }

    public void setStrCatName(String strCatName) {
        this.strCatName = strCatName;
    }
}
