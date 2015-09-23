package com.applicotest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nirajan on 9/22/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("date")
    private String commitDate;

    public Author() {}
    public Author(String name, String email, String commitDate) {
        this.name = name;
        this.email = email;
        this.commitDate = commitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }
}
