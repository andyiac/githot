package com.knight.arch.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class Users<T extends Parcelable> {

    @Expose
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
