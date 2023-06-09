package com.example.mycapi2.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mycapi2.database.save.Data;

import java.util.List;

@Dao public interface PlayerDAO
{
    @Query("SELECT * FROM data")
    List<Data> getAll();

    @Update
    void update(Data data);

    @Insert
    void insert(Data data);

    @Delete
    void delete(Data data);
}
