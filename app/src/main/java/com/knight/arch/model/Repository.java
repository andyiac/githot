package com.knight.arch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class Repository implements Parcelable {

    private int id;
    private String name;
    private String full_name;
    private User owner;
    @SerializedName("private")
    private boolean _private;
    private String html_url;
    private String description;
    private boolean fork;
    private String url;
    private String forks_url;
    private String keys_url;
    private String collaborators_url;
    private String teams_url;
    private String hooks_url;
    private String issue_events_url;
    private String events_url;
    private String assignees_url;
    private String branches_url;
    private String tags_url;
    private String blobs_url;
    private String git_tags_url;
    private String git_refs_url;
    private String trees_url;
    private String statuses_url;
    private String languages_url;
    private String stargazers_url;
    private String contributors_url;
    private String subscribers_url;
    private String subscription_url;
    private String commits_url;
    private String git_commits_url;
    private String comments_url;
    private String issue_comment_url;
    private String contents_url;
    private String compare_url;
    private String merges_url;
    private String archive_url;
    private String downloads_url;
    private String issues_url;
    private String pulls_url;
    private String milestones_url;
    private String notifications_url;
    private String labels_url;
    private String releases_url;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String git_url;
    private String ssh_url;
    private String clone_url;
    private String svn_url;
    private String homepage;
    private int size;
    private int stargazers_count;
    private int watchers_count;
    private String language;
    private boolean has_issues;
    private boolean has_downloads;
    private boolean has_wiki;
    private boolean has_pages;
    private int forks_count;
    private String mirror_url;
    private int open_issues_count;
    private int forks;
    private int open_issues;
    private int watchers;
    private String default_branch;
    private int score;

    protected Repository(Parcel in) {
        id = in.readInt();
        name = in.readString();
        full_name = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        _private = in.readByte() != 0;
        html_url = in.readString();
        description = in.readString();
        fork = in.readByte() != 0;
        url = in.readString();
        forks_url = in.readString();
        keys_url = in.readString();
        collaborators_url = in.readString();
        teams_url = in.readString();
        hooks_url = in.readString();
        issue_events_url = in.readString();
        events_url = in.readString();
        assignees_url = in.readString();
        branches_url = in.readString();
        tags_url = in.readString();
        blobs_url = in.readString();
        git_tags_url = in.readString();
        git_refs_url = in.readString();
        trees_url = in.readString();
        statuses_url = in.readString();
        languages_url = in.readString();
        stargazers_url = in.readString();
        contributors_url = in.readString();
        subscribers_url = in.readString();
        subscription_url = in.readString();
        commits_url = in.readString();
        git_commits_url = in.readString();
        comments_url = in.readString();
        issue_comment_url = in.readString();
        contents_url = in.readString();
        compare_url = in.readString();
        merges_url = in.readString();
        archive_url = in.readString();
        downloads_url = in.readString();
        issues_url = in.readString();
        pulls_url = in.readString();
        milestones_url = in.readString();
        notifications_url = in.readString();
        labels_url = in.readString();
        releases_url = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        pushed_at = in.readString();
        git_url = in.readString();
        ssh_url = in.readString();
        clone_url = in.readString();
        svn_url = in.readString();
        homepage = in.readString();
        size = in.readInt();
        stargazers_count = in.readInt();
        watchers_count = in.readInt();
        language = in.readString();
        has_issues = in.readByte() != 0;
        has_downloads = in.readByte() != 0;
        has_wiki = in.readByte() != 0;
        has_pages = in.readByte() != 0;
        forks_count = in.readInt();
        mirror_url = in.readString();
        open_issues_count = in.readInt();
        forks = in.readInt();
        open_issues = in.readInt();
        watchers = in.readInt();
        default_branch = in.readString();
        score = in.readInt();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(full_name);
        dest.writeParcelable(owner, flags);
        dest.writeByte((byte) (_private ? 1 : 0));
        dest.writeString(html_url);
        dest.writeString(description);
        dest.writeByte((byte) (fork ? 1 : 0));
        dest.writeString(url);
        dest.writeString(forks_url);
        dest.writeString(keys_url);
        dest.writeString(collaborators_url);
        dest.writeString(teams_url);
        dest.writeString(hooks_url);
        dest.writeString(issue_events_url);
        dest.writeString(events_url);
        dest.writeString(assignees_url);
        dest.writeString(branches_url);
        dest.writeString(tags_url);
        dest.writeString(blobs_url);
        dest.writeString(git_tags_url);
        dest.writeString(git_refs_url);
        dest.writeString(trees_url);
        dest.writeString(statuses_url);
        dest.writeString(languages_url);
        dest.writeString(stargazers_url);
        dest.writeString(contributors_url);
        dest.writeString(subscribers_url);
        dest.writeString(subscription_url);
        dest.writeString(commits_url);
        dest.writeString(git_commits_url);
        dest.writeString(comments_url);
        dest.writeString(issue_comment_url);
        dest.writeString(contents_url);
        dest.writeString(compare_url);
        dest.writeString(merges_url);
        dest.writeString(archive_url);
        dest.writeString(downloads_url);
        dest.writeString(issues_url);
        dest.writeString(pulls_url);
        dest.writeString(milestones_url);
        dest.writeString(notifications_url);
        dest.writeString(labels_url);
        dest.writeString(releases_url);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(pushed_at);
        dest.writeString(git_url);
        dest.writeString(ssh_url);
        dest.writeString(clone_url);
        dest.writeString(svn_url);
        dest.writeString(homepage);
        dest.writeInt(size);
        dest.writeInt(stargazers_count);
        dest.writeInt(watchers_count);
        dest.writeString(language);
        dest.writeByte((byte) (has_issues ? 1 : 0));
        dest.writeByte((byte) (has_downloads ? 1 : 0));
        dest.writeByte((byte) (has_wiki ? 1 : 0));
        dest.writeByte((byte) (has_pages ? 1 : 0));
        dest.writeInt(forks_count);
        dest.writeString(mirror_url);
        dest.writeInt(open_issues_count);
        dest.writeInt(forks);
        dest.writeInt(open_issues);
        dest.writeInt(watchers);
        dest.writeString(default_branch);
        dest.writeInt(score);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean is_private() {
        return _private;
    }

    public void set_private(boolean _private) {
        this._private = _private;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public void setKeys_url(String keys_url) {
        this.keys_url = keys_url;
    }

    public String getCollaborators_url() {
        return collaborators_url;
    }

    public void setCollaborators_url(String collaborators_url) {
        this.collaborators_url = collaborators_url;
    }

    public String getTeams_url() {
        return teams_url;
    }

    public void setTeams_url(String teams_url) {
        this.teams_url = teams_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public String getIssue_events_url() {
        return issue_events_url;
    }

    public void setIssue_events_url(String issue_events_url) {
        this.issue_events_url = issue_events_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getAssignees_url() {
        return assignees_url;
    }

    public void setAssignees_url(String assignees_url) {
        this.assignees_url = assignees_url;
    }

    public String getBranches_url() {
        return branches_url;
    }

    public void setBranches_url(String branches_url) {
        this.branches_url = branches_url;
    }

    public String getTags_url() {
        return tags_url;
    }

    public void setTags_url(String tags_url) {
        this.tags_url = tags_url;
    }

    public String getBlobs_url() {
        return blobs_url;
    }

    public void setBlobs_url(String blobs_url) {
        this.blobs_url = blobs_url;
    }

    public String getGit_tags_url() {
        return git_tags_url;
    }

    public void setGit_tags_url(String git_tags_url) {
        this.git_tags_url = git_tags_url;
    }

    public String getGit_refs_url() {
        return git_refs_url;
    }

    public void setGit_refs_url(String git_refs_url) {
        this.git_refs_url = git_refs_url;
    }

    public String getTrees_url() {
        return trees_url;
    }

    public void setTrees_url(String trees_url) {
        this.trees_url = trees_url;
    }

    public String getStatuses_url() {
        return statuses_url;
    }

    public void setStatuses_url(String statuses_url) {
        this.statuses_url = statuses_url;
    }

    public String getLanguages_url() {
        return languages_url;
    }

    public void setLanguages_url(String languages_url) {
        this.languages_url = languages_url;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public void setStargazers_url(String stargazers_url) {
        this.stargazers_url = stargazers_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public String getSubscribers_url() {
        return subscribers_url;
    }

    public void setSubscribers_url(String subscribers_url) {
        this.subscribers_url = subscribers_url;
    }

    public String getSubscription_url() {
        return subscription_url;
    }

    public void setSubscription_url(String subscription_url) {
        this.subscription_url = subscription_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getGit_commits_url() {
        return git_commits_url;
    }

    public void setGit_commits_url(String git_commits_url) {
        this.git_commits_url = git_commits_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getIssue_comment_url() {
        return issue_comment_url;
    }

    public void setIssue_comment_url(String issue_comment_url) {
        this.issue_comment_url = issue_comment_url;
    }

    public String getContents_url() {
        return contents_url;
    }

    public void setContents_url(String contents_url) {
        this.contents_url = contents_url;
    }

    public String getCompare_url() {
        return compare_url;
    }

    public void setCompare_url(String compare_url) {
        this.compare_url = compare_url;
    }

    public String getMerges_url() {
        return merges_url;
    }

    public void setMerges_url(String merges_url) {
        this.merges_url = merges_url;
    }

    public String getArchive_url() {
        return archive_url;
    }

    public void setArchive_url(String archive_url) {
        this.archive_url = archive_url;
    }

    public String getDownloads_url() {
        return downloads_url;
    }

    public void setDownloads_url(String downloads_url) {
        this.downloads_url = downloads_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public void setPulls_url(String pulls_url) {
        this.pulls_url = pulls_url;
    }

    public String getMilestones_url() {
        return milestones_url;
    }

    public void setMilestones_url(String milestones_url) {
        this.milestones_url = milestones_url;
    }

    public String getNotifications_url() {
        return notifications_url;
    }

    public void setNotifications_url(String notifications_url) {
        this.notifications_url = notifications_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getReleases_url() {
        return releases_url;
    }

    public void setReleases_url(String releases_url) {
        this.releases_url = releases_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public String getSvn_url() {
        return svn_url;
    }

    public void setSvn_url(String svn_url) {
        this.svn_url = svn_url;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public void setHas_downloads(boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public boolean isHas_pages() {
        return has_pages;
    }

    public void setHas_pages(boolean has_pages) {
        this.has_pages = has_pages;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public String getMirror_url() {
        return mirror_url;
    }

    public void setMirror_url(String mirror_url) {
        this.mirror_url = mirror_url;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static Creator<Repository> getCREATOR() {
        return CREATOR;
    }
}