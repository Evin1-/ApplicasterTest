package com.example.applicastertest.data.entities;

import io.realm.RealmObject;

/**
 * Created by user on 3/29/17.
 */

public class SearchTerm extends RealmObject {
    private String term;

    public SearchTerm() {
    }

    public SearchTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
