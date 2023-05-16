package com.example.mycapi2.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mycapi2.Player;

public class DataSave
{
    public static final String DATA_PREFS = "DATA_PREFS";

    public static final String HEALTH_KEY = "HEALTH";
    public static final String HEALTH_CD_KEY = "HEALTH_CD";
    public static final String FOOD_KEY = "FOOD";
    public static final String FOOD_CD_KEY = "FOOD_CD";
    public static final String CLEAN_KEY = "CLEAN";
    public static final String CLEAN_CD_KEY = "CLEAN_CD";
    public static final String ENJOY_KEY = "ENJOY";
    public static final String ENJOY_CD_KEY = "ENJOY_CD";
    public static final String SCORE_KEY = "SCORE";
    public static final String TIME_COEFF_KEY = "TIME_COEFF";
    public static final String STATS_COEFF_KEY = "STATS_COEFF";
    public static final String DOWN_TIME_KEY = "DOWN_TIME";

    public static void save(Context context,
                            Data data)
    {
        SharedPreferences preferences = context.getSharedPreferences(DATA_PREFS,
                                                                     Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HEALTH_KEY, data.getPlayer().getHealthStats());
        editor.putInt(FOOD_KEY, data.getPlayer().getFoodStats());
        editor.putInt(CLEAN_KEY, data.getPlayer().getCleanStats());
        editor.putInt(ENJOY_KEY, data.getPlayer().getEnjoyStats());
        editor.putInt(DOWN_TIME_KEY, data.getPlayer().getCountdownTime());
        editor.putInt(SCORE_KEY, data.getScore());
        editor.putInt(TIME_COEFF_KEY, data.getTimeCoefficient());
        editor.putInt(STATS_COEFF_KEY, data.getStatsCoefficient());
        editor.apply();
    }

    public static void load(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(DATA_PREFS,
                                                                     Context.MODE_PRIVATE);
        Data data = new Data(preferences.getInt(SCORE_KEY, 0),
                             preferences.getInt(TIME_COEFF_KEY, 0),
                             preferences.getInt(STATS_COEFF_KEY, 0),
                             preferences.getInt(DOWN_TIME_KEY, 0),
                             new Player());
    }
}
