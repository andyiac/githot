package com.knight.arch.model;

/**
 * Created by summer on 15-7-30.
 *
 * @web http://blog.andyiac.com/
 */
public class PersonInfo {
    private String Rank;
    private String Gravatar;
    private String username;
    private String name;
    private String location;
    private String language;
    private String repos;
    private String followers;
    private String created;

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
}
