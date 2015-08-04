package com.knight.arch.ui.base;

import android.support.v4.app.Fragment;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class BaseFragment extends Fragment {


    public void setStatusColor(int color) {
        ((BaseActivity) getActivity()).setStatusColor(color);
    }
}
