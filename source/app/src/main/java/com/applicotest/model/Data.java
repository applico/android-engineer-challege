package com.applicotest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nirajan on 9/22/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @JsonProperty("commit")
    private Commit commit;
    @JsonProperty("committer")
    private Committer committer;


    public Data(){

    }

    public Data(Commit commit, Committer committer) {
        this.commit = commit;
        this.committer = committer;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }


}
