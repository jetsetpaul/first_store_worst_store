package jetsetpaul.e_commerce_app;

import java.io.Serializable;

/**
 * Created by pauljoiner on 6/25/16.
 */
public class Product implements Serializable {
    public String title;
    public String artist;
    public String category;
    public String description;
    public String price;
    public int imageId;
    public String media;


    Product(String title, String artist, String category, String description, String price, int imageId, String media) {
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice(){
        return price;
    }

    public int getImageId() {
        return imageId;
    }

    public String getMedia(){
        return media;
    }
    public String toString(){
        return title + " " + description + " " + price;
    }

}
