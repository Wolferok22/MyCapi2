package com.example.mycapi2.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycapi2.database.save.Data;

@Database(entities = {Data.class}, version = 1)
public abstract class DataBase extends RoomDatabase
{
    public abstract PlayerDAO playerDAO();
    private static DataBase instance;
    static DataBase getInstance(Context context)
    {
        if (instance == null)
        {
            synchronized (DataBase.class)
            {
                if (instance == null)
                {
                    instance = Room.databaseBuilder(context.getApplicationContext(), DataBase.class,
                                                    "SCORES").build();
                }
            }
        }
        return instance;
    }




}
