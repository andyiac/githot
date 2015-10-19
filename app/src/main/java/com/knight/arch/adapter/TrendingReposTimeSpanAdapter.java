package com.knight.arch.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knight.arch.R;


/**
 * @author andyiac
 * @date 15/10/19
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class TrendingReposTimeSpanAdapter extends BaseAdapter {
    protected Context mContext;

    public TrendingReposTimeSpanAdapter(Context context) {
        this.mContext = context;
    }

    final String[] mTimeSpanArray = new String[]{"Trending today", "Trending this week", "Trending this month"};

    @Override
    public int getCount() {
        return mTimeSpanArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mTimeSpanArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.trending_repos_timespan_list_item_actionbar, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getItem(position).toString());

        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.trending_repos_timespan_list_item_dropdown, parent, false);
            view.setTag("NON_DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getItem(position).toString());

        return view;
    }

}
