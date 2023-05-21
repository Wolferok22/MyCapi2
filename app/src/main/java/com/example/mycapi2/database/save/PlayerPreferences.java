package com.example.mycapi2.database.save;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mycapi2.models.Player;

public class PlayerPreferences
{
    public static final String PLAYER_PREFS = "PLAYER_PREFS";

    public static final String HEALTH_KEY = "HEALTH";
    public static final String FOOD_KEY = "FOOD";
    public static final String CLEAN_KEY = "CLEAN";
    public static final String ENJOY_KEY = "ENJOY";

    public void save(Context context, Player player){
        SharedPreferences preferences = context.getSharedPreferences(PLAYER_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HEALTH_KEY, player.getHealthStats());
        editor.putInt(FOOD_KEY, player.getFoodStats());
        editor.putInt(CLEAN_KEY, player.getCleanStats());
        editor.putInt(ENJOY_KEY, player.getEnjoyStats());
        editor.apply();
    }
    public void load(Context context, Player player){
        SharedPreferences preferences = context.getSharedPreferences(PLAYER_PREFS,
                                                                     Context.MODE_PRIVATE);
        player.setHealthStats(preferences.getInt(HEALTH_KEY,0));
        player.setFoodStats(preferences.getInt(FOOD_KEY,0));
        player.setCleanStats(preferences.getInt(CLEAN_KEY,0));
        player.setEnjoyStats(preferences.getInt(ENJOY_KEY,0));
    }
}
