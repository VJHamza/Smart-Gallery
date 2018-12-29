package com.androstock.galleryapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Image.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract ImageDao imageDao();
    private static AppRoomDatabase instance;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (instance== null) {
            synchronized (AppRoomDatabase.class) {
                if (instance== null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "Gallery_app").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

}