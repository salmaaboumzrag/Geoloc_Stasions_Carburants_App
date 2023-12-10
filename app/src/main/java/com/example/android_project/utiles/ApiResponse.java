package com.example.android_project.utiles;

import com.example.android_project.modle.StationCarburant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("results")
    @Expose
    private List<StationCarburant> results;

    /**
     * No args constructor for use in serialization
     */
    public ApiResponse() {
    }

    /**
     * @param totalCount
     * @param results
     */
    public ApiResponse(Integer totalCount, List<StationCarburant> results) {
        super();
        this.totalCount = totalCount;
        this.results = results;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<StationCarburant> getResults() {
        return results;
    }

    public void setResults(List<StationCarburant> results) {
        this.results = results;
    }
}

