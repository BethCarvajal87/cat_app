package com.proyectosbeth;

public class FavoriteCats {

    String id;
    String image_id;
    String apiKey="live_x5cSeFjTZSvPspNjtAq3EIXQMeNGIZywA1IYv97gCeERaG0qFpl4ZcB3bC4vo890";
    ImageX imageX;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ImageX getImageX() {
        return imageX;
    }

    public void setImageX(ImageX imageX) {
        this.imageX = imageX;
    }
}
