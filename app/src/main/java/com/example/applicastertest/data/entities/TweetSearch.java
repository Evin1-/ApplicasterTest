package com.example.applicastertest.data.entities;

import com.twitter.sdk.android.core.models.Tweet;

import io.realm.RealmObject;

/**
 * Created by user on 3/29/17.
 */

public class TweetSearch extends RealmObject {
    private SearchTerm searchTerm;
    private String userName;
    private String userPicture;
    private Integer userFollowers;
    private String text;
    private String image;

    public TweetSearch() {

    }

    public TweetSearch(SearchTerm searchTerm, String userName, String userPicture, Integer userFollowers, String text, String image) {
        this.searchTerm = searchTerm;
        this.userName = userName;
        this.userPicture = userPicture;
        this.userFollowers = userFollowers;
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

    public Integer getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(Integer userFollowers) {
        this.userFollowers = userFollowers;
    }

    public static TweetSearch create(SearchTerm searchTerm, Tweet tweet) {
        return new TweetSearch.Builder()
                .setSearchTerm(searchTerm)
                .setUserName(tweet.user.name)
                .setUserPicture(tweet.user.profileBackgroundImageUrl)
                .setUserFollowers(tweet.user.followersCount)
                .setText(tweet.text)
                .build();
    }

    public static TweetSearch create(Tweet tweet) {
        return new TweetSearch.Builder()
                .setUserName(tweet.user.name)
                .setUserPicture(tweet.user.profileBackgroundImageUrl)
                .setUserFollowers(tweet.user.followersCount)
                .setText(tweet.text)
                .build();
    }

    public static class Builder {
        private SearchTerm searchTerm;
        private String userName;
        private String userPicture;
        private Integer userFollowers;
        private String text;
        private String image;

        public Builder setSearchTerm(SearchTerm searchTerm) {
            this.searchTerm = searchTerm;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setUserPicture(String userPicture) {
            this.userPicture = userPicture;
            return this;
        }

        public Builder setUserFollowers(Integer userFollowers) {
            this.userFollowers = userFollowers;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public TweetSearch build() {
            return new TweetSearch(searchTerm, userName, userPicture, userFollowers, text, image);
        }
    }
}
