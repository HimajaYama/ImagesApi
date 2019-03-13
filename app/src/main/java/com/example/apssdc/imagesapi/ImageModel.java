package com.example.apssdc.imagesapi;

public class ImageModel {

    String tags, images,likes,views;

    public ImageModel(String images, String likes, String views, String tags) {
        this.likes = likes;
        this.views = views;
        this.tags = tags;
        this.images = images;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}

