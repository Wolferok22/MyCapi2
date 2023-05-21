package com.example.mycapi2.database.save;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mycapi2.viewmodels.MainViewModel;

public class DataSave
{
    public static final String DATA_PREFS = "DATA_PREFS";

    public static final String HEALTH_CD_KEY = "HEALTH_CD";
    public static final String FOOD_CD_KEY = "FOOD_CD";

    public static final String CLEAN_CD_KEY = "CLEAN_CD";
    public static final String ENJOY_CD_KEY = "ENJOY_CD";
    public static final String SCORE_KEY = "SCORE";
    public static final String TIME_COEFF_KEY = "TIME_COEFF";
    public static final String STATS_COEFF_KEY = "STATS_COEFF";
    public static final String DOWN_TIME_KEY = "DOWN_TIME";

    public static void save(Context context,
                            Data data, MainViewModel mainViewModel)
    {
        SharedPreferences preferences = context.getSharedPreferences(DATA_PREFS,
                                                                     Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HEALTH_CD_KEY, mainViewModel.getCurrentHealthCountdown());
        editor.putInt(FOOD_CD_KEY, mainViewModel.getCurrentFoodCountdown());
        editor.putInt(CLEAN_CD_KEY, mainViewModel.getCurrentCleanCountdown());
        editor.putInt(ENJOY_CD_KEY, mainViewModel.getCurrentEnjoyCountdown());
        editor.putInt(DOWN_TIME_KEY, mainViewModel.getStatsDownTime());
        editor.putInt(SCORE_KEY, data.getScore());
        editor.putInt(TIME_COEFF_KEY, data.getTimeCoefficient());
        editor.putInt(STATS_COEFF_KEY, data.getStatsCoefficient());
        editor.apply();
    }

    public static void load(Context context, MainViewModel viewModel)
    {
        SharedPreferences preferences = context.getSharedPreferences(DATA_PREFS,
                                                                     Context.MODE_PRIVATE);
        Data data = new Data(preferences.getInt(SCORE_KEY, 0),
                             preferences.getInt(TIME_COEFF_KEY, 0),
                             preferences.getInt(STATS_COEFF_KEY, 0));
        viewModel.setCurrentHealthCountdown(preferences.getInt(HEALTH_CD_KEY, 0));
        viewModel.setCurrentFoodCountdown(preferences.getInt(FOOD_CD_KEY, 0));
        viewModel.setCurrentCleanCountdown(preferences.getInt(CLEAN_CD_KEY, 0));
        viewModel.setCurrentEnjoyCountdown(preferences.getInt(ENJOY_CD_KEY, 0));
        viewModel.setStatsDownTime(preferences.getInt(DOWN_TIME_KEY, 0));
    }
}
