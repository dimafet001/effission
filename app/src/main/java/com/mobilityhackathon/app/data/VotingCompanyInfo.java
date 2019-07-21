package com.mobilityhackathon.app.data;

import android.view.View;

public class VotingCompanyInfo {

    private boolean isChosen;
    private String name, pathToImage;
    private int idx;
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public VotingCompanyInfo(int idx, String name, boolean isChosen, String pathToImage) {
        this.isChosen = isChosen;
        this.name = name;
        this.idx = idx;
        this.pathToImage = pathToImage;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
