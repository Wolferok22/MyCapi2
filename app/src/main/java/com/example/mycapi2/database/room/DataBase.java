package com.example.mycapi2.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mycapi2.database.Data;

@Database(entities = {Data.class}, version = 1)
public abstract class DataBase extends RoomDatabase
{
    public abstract PlayerDAO playerDAO();
}
