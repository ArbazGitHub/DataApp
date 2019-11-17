package com.example.dataapp.models.home;

import java.io.Serializable;

public class LeftMenuMain implements Serializable {
    public String strMenuTitle;
    public int imgId;

    public LeftMenuMain(String strMenuTitle) {
        this.strMenuTitle = strMenuTitle;
    }

    public LeftMenuMain(String strMenuTitle, int imgId) {
        this.strMenuTitle = strMenuTitle;
        this.imgId = imgId;
    }

    public String getStrMenuTitle() {
        return strMenuTitle;
    }

    public void setStrMenuTitle(String strMenuTitle) {
        this.strMenuTitle = strMenuTitle;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}


