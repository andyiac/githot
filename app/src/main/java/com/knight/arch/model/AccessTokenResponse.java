package com.knight.arch.model;

/**
 * @author andyiac
 * @date 15/10/23
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 * {"access_token":"e72e16c7e42f292c6912e7710c838347ae178b4a", "scope":"repo,gist", "token_type":"bearer"}
 */

public class AccessTokenResponse {

    private String access_token;
    private String scope;
    private String token_type;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
