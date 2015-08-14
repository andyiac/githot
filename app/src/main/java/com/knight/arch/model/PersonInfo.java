package com.knight.arch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by summer on 15-7-30.
 *
 * @web http://blog.andyiac.com/
 */
public class PersonInfo implements Parcelable {
    public static final Creator<PersonInfo> CREATOR = new Creator<PersonInfo>() {
        @Override
        public PersonInfo createFromParcel(Parcel in) {
            return new PersonInfo(in);
        }

        @Override
        public PersonInfo[] newArray(int size) {
            return new PersonInfo[size];
        }
    };
    @SerializedName("rank")
    private String Rank;
    @SerializedName("gravatar")
    private String Gravatar;
    private String username;
    private String name;
    private String location;
    private String language;
    private String repos;
    private String followers;
    private String created;

    protected PersonInfo(Parcel in) {
        Rank = in.readString();
        Gravatar = in.readString();
        username = in.readString();
        name = in.readString();
        location = in.readString();
        language = in.readString();
        repos = in.readString();
        followers = in.readString();
        created = in.readString();
    }

    public String getGravatar() {
        return Gravatar;
    }

    public void setGravatar(String gravatar) {
        Gravatar = gravatar;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Rank);
        dest.writeString(Gravatar);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(language);
        dest.writeString(repos);
        dest.writeString(followers);
        dest.writeString(created);
    }
}
