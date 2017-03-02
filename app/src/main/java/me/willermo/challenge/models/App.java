package me.willermo.challenge.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by william on 3/1/17.
 */

public class App extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String title;
    private String summary;
    private String image;

    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
