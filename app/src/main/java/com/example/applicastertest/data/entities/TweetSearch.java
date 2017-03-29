package com.example.applicastertest.data.entities;

import io.realm.RealmObject;

/**
 * Created by user on 3/29/17.
 */

public class TweetSearch extends RealmObject {
    private SearchTerm searchTerm;
    private String userName;
    private String userPicture;
    private String text;
    private String image;

    public TweetSearch() {
    }

    public TweetSearch(SearchTerm searchTerm, String userName, String userPicture, String text, String image) {
        this.searchTerm = searchTerm;
        this.userName = userName;
        this.userPicture = userPicture;
        this.text = text;
        this.image = image;
    }

    public SearchTerm getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(SearchTerm searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
