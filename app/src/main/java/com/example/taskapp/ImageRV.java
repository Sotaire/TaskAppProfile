package com.example.taskapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageRV {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String uri;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
