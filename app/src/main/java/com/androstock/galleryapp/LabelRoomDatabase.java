//package com.androstock.galleryapp;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//
//@Database(entities = {Labels.class}, version = 1)
//public abstract class LabelRoomDatabase extends RoomDatabase {
//    public abstract LabelDao labelDao();
//    private static volatile LabelRoomDatabase INSTANCE;
//
//    static LabelRoomDatabase getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (LabelRoomDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            LabelRoomDatabase.class, "Gallery_app")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//}