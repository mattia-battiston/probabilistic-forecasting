package com.pft.entrypoint.rest.leadtime.dto;

public class RequestDto {

    private int noOfStories;
    private int noOfSamples;

    public RequestDto(int noOfStories, int noOfSamples) {
        this.noOfStories = noOfStories;
        this.noOfSamples = noOfSamples;
    }

    public RequestDto() {
    }

    public int getNoOfStories() {
        return noOfStories;
    }

    public int getNoOfSamples() {
        return noOfSamples;
    }
}
