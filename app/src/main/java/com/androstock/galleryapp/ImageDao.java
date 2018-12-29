package com.androstock.galleryapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert
    void insert(Image image);

    @Query("DELETE  FROM Image_table")
    void deleteAll();

    @Query("SELECT * from Image_table where Labels like :searchVariable")
    List<Image> searchImageByLabel(String searchVariable);
}
