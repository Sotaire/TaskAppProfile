package com.example.taskapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskapp.ImageRV;
import com.example.taskapp.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM imageRV")
    List<ImageRV> getAllImage();

    @Query("SELECT * FROM imageRV")
    LiveData<List<ImageRV>> getAllLiveImage();

    @Insert
    void insertImage(ImageRV imageRV);

    @Update
    void updateImage(ImageRV imageRV);

    @Delete
    void deleteImage(List<ImageRV> imageRV);

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllLive();

    @Insert
    void insert (Task task);

    @Update
    void update (Task task);

    @Delete
    void delete(Task task);

    @Delete
    void deleteAll(List<Task> task);

    @Query("SELECT * FROM task ORDER BY task.title ASC")
    List<Task> getTaskSortByAscLastName();

    @Query("SELECT * FROM task ORDER BY task.title DESC")
    List<Task> getTaskSortByDescLastName();

    @Query("SELECT * FROM task ORDER by task.createdAt DESC")
    List<Task> getTaskSortDate();

    @Query("SELECT * FROM task ORDER by task.createdAt ASC")
    List<Task> getTaskSortDate2();

    @Query("SELECT * FROM Task ORDER BY CASE WHEN :isAsc = 1 THEN task.title END ASC, CASE WHEN :isAsc = 0 THEN task.title END DESC")
    List<Task> getTaskAlphabetically(boolean isAsc);

    @Query("SELECT * FROM Task ORDER BY CASE WHEN :isAsc = 1 THEN task.createdAt END ASC, CASE WHEN :isAsc = 0 THEN task.createdAt END DESC")
    List<Task> getTaskSortDate(boolean isAsc);
}
