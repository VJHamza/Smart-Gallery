package com.androstock.galleryapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "Image_table")
public class Image implements Serializable {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int id;

    private String Labels;

    private String uri;

    public Image(){}
    public Image(String labels)
    {this.Labels = labels;}

    public Image( String labels, String uri) {
        Labels = labels;
        this.uri = uri;
    }

    public String getLabels(){return this.Labels;}

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setLabels(String labels) {
        Labels = labels;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
