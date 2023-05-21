package com.example.mycapi2.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.mycapi2.database.room.DataRepository;
import com.example.mycapi2.database.save.Data;
import com.example.mycapi2.models.Player;

import java.util.List;

public class MainViewModel extends AndroidViewModel
{
    private DataRepository dataRepository;
    private Player player;
    private int clickPower = 10; // увеличивается за улучшение клика
    private int clickCountdown = 40; //уменьшается за улучшение
    private int statsDownTime = 1;

    private float timeCoefficient = 1;
    private int score = 0;
    private int currentHealthCountdown;
    private int currentFoodCountdown;
    private int currentCleanCountdown;
    private int currentEnjoyCountdown;

    public MainViewModel(Application application)
    {
        super(application);
        dataRepository = DataRepository.getInstance(application);
    }

    public List<Data> getAll()
    {
        return dataRepository.getAll();
    }

    public void insert(Data data)
    {
        dataRepository.insert(data);
    }

    public void delete(Data data)
    {
        dataRepository.delete(data);
    }

    public void update(Data data)
    {
        dataRepository.update(data);
    }

    public int getStatsDownTime()
    {
        return statsDownTime;
    }

    public void setStatsDownTime(int statsDownTime)
    {
        this.statsDownTime = statsDownTime;
    }

    public float getTimeCoefficient()
    {
        return timeCoefficient;
    }

    public void setTimeCoefficient(float timeCoefficient)
    {
        this.timeCoefficient = timeCoefficient;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public int getClickPower()
    {
        return clickPower;
    }

    public void setClickPower(int clickPower)
    {
        this.clickPower = clickPower;
    }

    public int getClickCountdown()
    {
        return clickCountdown;
    }

    public void setClickCountdown(int clickCountdown)
    {
        this.clickCountdown = clickCountdown;
    }

    public void incrementCurrentHealthCountdown()
    {
        this.currentHealthCountdown = this.currentHealthCountdown + 1;
    }

    public void incrementCurrentFoodCountdown()
    {
        this.currentFoodCountdown = this.currentFoodCountdown + 1;
    }

    public void incrementCurrentCleanCountdown()
    {
        this.currentCleanCountdown = this.currentCleanCountdown + 1;
    }

    public void incrementCurrentEnjoyCountdown()
    {
        this.currentEnjoyCountdown = this.currentEnjoyCountdown + 1;
    }

    public int getCurrentHealthCountdown()
    {
        return currentHealthCountdown;
    }

    public void setCurrentHealthCountdown(int currentHealthCountdown)
    {
        this.currentHealthCountdown = currentHealthCountdown;
    }

    public int getCurrentFoodCountdown()
    {
        return currentFoodCountdown;
    }

    public void setCurrentFoodCountdown(int currentFoodCountdown)
    {
        this.currentFoodCountdown = currentFoodCountdown;
    }

    public int getCurrentCleanCountdown()
    {
        return currentCleanCountdown;
    }

    public void setCurrentCleanCountdown(int currentCleanCountdown)
    {
        this.currentCleanCountdown = currentCleanCountdown;
    }

    public int getCurrentEnjoyCountdown()
    {
        return currentEnjoyCountdown;
    }

    public void setCurrentEnjoyCountdown(int currentEnjoyCountdown)
    {
        this.currentEnjoyCountdown = currentEnjoyCountdown;
    }
    public void incrementClickPower(){
        this.clickPower = clickPower + 2;
    }
    public void decrementClickCountdown(){
        this.clickCountdown = clickCountdown - 2;
    }
}
