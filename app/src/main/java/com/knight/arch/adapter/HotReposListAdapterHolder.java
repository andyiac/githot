/*
 * Copyright (C) 2014 VenomVendor <info@VenomVendor.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.knight.arch.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knight.arch.R;
import com.knight.arch.model.Repository;
import com.knight.arch.utils.L;

import java.util.List;

public class HotReposListAdapterHolder extends RecyclerView.Adapter<HotReposListAdapterHolder.ViewHolder> {

    OnItemClickListener mItemClickListener;
    private FragmentActivity mActivity;
    private List<Repository> mRepos;

    public HotReposListAdapterHolder(FragmentActivity mActivity, List<Repository> repos) {
        this.mActivity = mActivity;
        this.mRepos = repos;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.hot_repos_single_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mRepos.get(position).getName());
        holder.tvStars.setText("stars: " + mRepos.get(position).getStargazers_count());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvStars;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.id_repos_name);
            tvStars = (TextView) view.findViewById(R.id.id_repos_stars);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                L.i("==========click=====");
//                mItemClickListener.onItemClick(v, getPosition());
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }

    }

}
