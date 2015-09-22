package com.knight.arch.ui.base;

import android.support.v4.app.Fragment;


/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class BaseFragment extends Fragment {
    public void setStatusColor(int color) {
        ((BaseActivity) getActivity()).setStatusColor(color);
    }
}
