package com.knight.arch.events;

import android.net.Uri;

/**
 * @author andyiac
 * @date 15/10/23
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class LoginUriMsg {
    private Uri url;

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }
}
