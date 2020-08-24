package com.example.taskapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskapp.ImageRV;
import com.example.taskapp.models.Task;

@Database(entities = {Task.class, ImageRV.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
