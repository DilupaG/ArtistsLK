package com.example.artistslk.Model;

public class Track {

    private String trackName;
    private String trackID;
    private int trackRating;

    public Track() {
    }

    public Track(String trackName, String trackID, int trackRating) {
        this.trackName = trackName;
        this.trackID = trackID;
        this.trackRating = trackRating;
    }

    public Track(String trackName, int trackRating) {
        this.trackName = trackName;
        this.trackRating = trackRating;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public void setTrackRating(int trackRating) {
        this.trackRating = trackRating;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackID() {
        return trackID;
    }

    public int getTrackRating() {
        return trackRating;
    }
}
