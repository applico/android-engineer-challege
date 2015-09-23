package com.applicotest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A model class that represents the commit message returned from the github api call
 *
 * Created by Nirajan on 9/22/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
    private Author author;
    @JsonProperty("message")
    private String message;
    @JsonProperty("url")
    private String commitUrl;

    public Commit() {}

    public Commit(Author author, String message, String commitUrl) {
        this.author = author;
        this.message = message;
        this.commitUrl = commitUrl;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        this.commitUrl = commitUrl;
    }
}
