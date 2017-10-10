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

package com.knight.arch.ui.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.knight.arch.R;
import com.knight.arch.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GitHubUserRankListAdapterHolder extends RecyclerView.Adapter<GitHubUserRankListAdapterHolder.ViewHolder> {

    OnItemClickListener mItemClickListener;
    private FragmentActivity mActivity;
    private List<User> mUsers;
    private Picasso picasso;

    public GitHubUserRankListAdapterHolder(FragmentActivity mActivity, List<User> users, Picasso picasso) {
        this.mActivity = mActivity;
        this.mUsers = users;
        this.picasso = picasso;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.github_user_rank_single_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //picasso.load(mUsers.get(position).getAvatar_url()).placeholder(R.mipmap.avatar).into(holder.ivAvatar);
        String avatar_url = "https://avatars2.githubusercontent.com/u/" + mUsers.get(position).getId() + "?s=140";
        picasso.load(avatar_url).placeholder(R.mipmap.avatar).into(holder.ivAvatar);
        holder.tvLogin.setText(mUsers.get(position).getLogin());
        holder.tvRank.setText("rank: " + String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvLogin;
        ImageView ivAvatar;
        TextView tvRank;

        public ViewHolder(View view) {
            super(view);
            tvRank = (TextView) view.findViewById(R.id.id_user_rank_rankings);
            ivAvatar = (ImageView) view.findViewById(R.id.id_user_rank_avatar);
            tvLogin = (TextView) view.findViewById(R.id.id_user_rank_login);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                // mItemClickListener.onItemClick(v, getPosition());
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }

    }

}
