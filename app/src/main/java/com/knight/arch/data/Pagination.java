package com.knight.arch.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class Pagination<T extends Parcelable> {

    @Expose
    private List<T> data;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
