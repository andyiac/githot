package com.knight.arch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author andyiac
 * @date 15-9-25
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class FirVersion {

    @Expose
    private String name;
    @Expose
    private Integer version;
    @SerializedName("changelog")
    @Expose
    private String changeLog;
    @Expose
    private String versionShort;
    @Expose
    private String installUrl;
    @SerializedName("update_url")
    @Expose
    private String updateUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }
}
