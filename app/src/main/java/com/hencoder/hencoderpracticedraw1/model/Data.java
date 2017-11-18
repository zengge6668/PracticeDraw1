package com.hencoder.hencoderpracticedraw1.model;

import android.graphics.Color;

/**
 * Created by zengbin on 2017/11/16.
 */

public class Data {
    String name;
    float number;
    int color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Data(String name, float number, int color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }
}
