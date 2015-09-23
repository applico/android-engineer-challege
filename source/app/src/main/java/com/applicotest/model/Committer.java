package com.applicotest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nirajan on 9/22/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Committer {
    @JsonProperty("avatar_url")
    private String imageUrl;

    public Committer() {}
    public Committer(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
