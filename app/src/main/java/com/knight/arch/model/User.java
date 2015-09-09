package com.knight.arch.model;

/**
 * @author andyiac
 * @date 15-9-9
 * @web http://blog.andyiac.com/
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * For github api: https://api.github.com/search/users?q=location:china&page=1
 * <p/>
 * "login": "ruanyf",
 * "id": 905434,
 * "avatar_url": "https://avatars.githubusercontent.com/u/905434?v=3",
 * "gravatar_id": "",
 * "url": "https://api.github.com/users/ruanyf",
 * "html_url": "https://github.com/ruanyf",
 * "followers_url": "https://api.github.com/users/ruanyf/followers",
 * "following_url": "https://api.github.com/users/ruanyf/following{/other_user}",
 * "gists_url": "https://api.github.com/users/ruanyf/gists{/gist_id}",
 * "starred_url": "https://api.github.com/users/ruanyf/starred{/owner}{/repo}",
 * "subscriptions_url": "https://api.github.com/users/ruanyf/subscriptions",
 * "organizations_url": "https://api.github.com/users/ruanyf/orgs",
 * "repos_url": "https://api.github.com/users/ruanyf/repos",
 * "events_url": "https://api.github.com/users/ruanyf/events{/privacy}",
 * "received_events_url": "https://api.github.com/users/ruanyf/received_events",
 * "type": "User",
 * "site_admin": false,
 * "score": 1
 */
public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private String login;
    private int id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String followering_url;
    private String gists_url;
    private String statted_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private Boolean site_admin;
    private int score;

    protected User(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        followering_url = in.readString();
        gists_url = in.readString();
        statted_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        score = in.readInt();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowering_url() {
        return followering_url;
    }

    public void setFollowering_url(String followering_url) {
        this.followering_url = followering_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStatted_url() {
        return statted_url;
    }

    public void setStatted_url(String statted_url) {
        this.statted_url = statted_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSite_admin() {
        return site_admin;
    }

    public void setSite_admin(Boolean site_admin) {
        this.site_admin = site_admin;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(id);
        dest.writeString(avatar_url);
        dest.writeString(gravatar_id);
        dest.writeString(url);
        dest.writeString(html_url);
        dest.writeString(followers_url);
        dest.writeString(followering_url);
        dest.writeString(gists_url);
        dest.writeString(statted_url);
        dest.writeString(subscriptions_url);
        dest.writeString(organizations_url);
        dest.writeString(repos_url);
        dest.writeString(events_url);
        dest.writeString(received_events_url);
        dest.writeString(type);
        dest.writeInt(score);
    }
}
