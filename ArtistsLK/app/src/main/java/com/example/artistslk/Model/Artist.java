package com.example.artistslk.Model;

public class Artist {

    private String artistID;
    private String artistName;
    private String artistGenre;

    public Artist() {
    }

    public Artist(String artistID, String artistName, String artistGenre) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public Artist(String artistName, String artistGenre) {
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistGenre(String artistGenre) {
        this.artistGenre = artistGenre;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

}
