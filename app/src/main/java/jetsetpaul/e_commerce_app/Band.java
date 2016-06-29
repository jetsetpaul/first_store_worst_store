package jetsetpaul.e_commerce_app;

import java.io.Serializable;

/**
 * Created by pauljoiner on 6/28/16.
 */
public class Band implements Serializable {
    public String artist;
    public String genre;
    public String location;

    Band(String artist, String genre, String location){
        this.artist = artist;
        this.genre = genre;
        this.location = location;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getLocation() {
        return location;
    }
}
