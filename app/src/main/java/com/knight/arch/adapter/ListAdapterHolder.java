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
import android.widget.ImageView;
import android.widget.TextView;

import com.knight.arch.R;
import com.knight.arch.model.PersonInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapterHolder extends RecyclerView.Adapter<ListAdapterHolder.ViewHolder> {

    OnItemClickListener mItemClickListener;
    private FragmentActivity mActivity;
    private List<PersonInfo> mPersonInfos;//= new ArrayList<PersonInfo>();
    private Picasso picasso;

    public ListAdapterHolder(FragmentActivity mActivity, List<PersonInfo> mPersonInfos, Picasso picasso) {
        this.mActivity = mActivity;
        this.mPersonInfos = mPersonInfos;
        this.picasso = picasso;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.single_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.vRank.setText("Rank: " + mPersonInfos.get(position).getRank());
//        holder.vGravatar.setText("Gravatar: " + mPersonInfos.get(position).getGravatar());
        picasso.load(mPersonInfos.get(position).getGravatar()).placeholder(R.drawable.holde_image).into(holder.vGravatar);
        holder.vUsername.setText("Username: " + mPersonInfos.get(position).getUsername());
        holder.vName.setText("Name: " + mPersonInfos.get(position).getName());
        holder.vLocation.setText("Rank: " + mPersonInfos.get(position).getLocation());
        holder.vLanguage.setText("Language: " + mPersonInfos.get(position).getLanguage());
        holder.vRepos.setText("Repos: " + mPersonInfos.get(position).getRepos());
        holder.vFollowers.setText("Follower: " + mPersonInfos.get(position).getFollowers());
        holder.vCreated.setText("Created: " + mPersonInfos.get(position).getCreated());
    }

    @Override
    public int getItemCount() {
        return mPersonInfos.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView vRank, vUsername, vName, vLocation, vLanguage, vRepos, vFollowers, vCreated;
        ImageView vGravatar;

        public ViewHolder(View view) {
            super(view);
            vRank = (TextView) view.findViewById(R.id.list_rank);
            vGravatar = (ImageView) view.findViewById(R.id.list_gravatar);
            vUsername = (TextView) view.findViewById(R.id.list_username);
            vName = (TextView) view.findViewById(R.id.list_name);
            vLocation = (TextView) view.findViewById(R.id.list_location);
            vLanguage = (TextView) view.findViewById(R.id.list_language);
            vRepos = (TextView) view.findViewById(R.id.list_repos);
            vFollowers = (TextView) view.findViewById(R.id.list_followers);
            vCreated = (TextView) view.findViewById(R.id.list_created);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }

    }

}
