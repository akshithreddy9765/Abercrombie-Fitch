package com.example.akshi.afpomotion;

import java.io.Serializable;

/**
 * Created by akshi on 6/15/2016.
 */
public class AFPromotionModel implements Serializable {


    private String description, footer, title, image;
    private String btntitle;
    private String btntarget;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getBtntitle() {
        return btntitle;
    }

    public void setBtntitle(String btntitle) {
        this.btntitle = btntitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBtntarget() {
        return btntarget;
    }

    public void setBtntarget(String btntarget) {
        this.btntarget = btntarget;
    }
}
