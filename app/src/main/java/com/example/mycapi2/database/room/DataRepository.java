package com.example.mycapi2.database.room;

import android.content.Context;
import android.content.res.Resources;

import androidx.room.Room;

import com.example.mycapi2.database.save.Data;
import com.example.mycapi2.models.ShopProduct;

import java.util.ArrayList;
import java.util.List;

public class DataRepository
{
    private static DataRepository instance = null;
    public DataBase roomdb;

    protected DataRepository(Context context)
    {
        roomdb = Room.databaseBuilder(context, DataBase.class, "scores")
                     .allowMainThreadQueries()
                     .build();
    }

    public static DataRepository getInstance(Context context)
    {
        if (instance == null) instance = new DataRepository(context);
        return instance;
    }

    public List<Data> getAll()
    {
        return roomdb.playerDAO().getAll();
    }

    public void insert(Data data)
    {
        roomdb.playerDAO().insert(data);
    }

    public void delete(Data data)
    {
        roomdb.playerDAO().delete(data);
    }

    public void update(Data data)
    {
        roomdb.playerDAO().update(data);
    }


}
