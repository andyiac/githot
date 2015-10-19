package com.knight.arch.adapter;

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

    final String[] mTimeSpanArray = new String[]{"daily", "weekly", "monthly"};

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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.trending_repos_timespan_list_item, null);
            textView = (TextView) convertView.findViewById(R.id.tv_id_trending_repos_time_span);

            convertView.setTag(new ViewHolder(textView));

        } else {
            textView = ((ViewHolder) convertView.getTag()).textView;
        }

        textView.setText(getItem(position).toString());

        return convertView;
    }

    protected static class ViewHolder {

        public TextView textView;

        public ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
