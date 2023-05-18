package com.example.mycapi2.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.mycapi2.models.Player;
import com.example.mycapi2.utils.buttoncountdown.ButtonCountdown;

public class MainViewModel extends ViewModel
{
    private Player player;
    private int clickPower = 10; // увеличивается за улучшение клика
    private int clickCountdown = 40; //уменьшается за улучшение

    private float timeCoefficient = 1;
    private int score = 0;
    private int currentHealthCountdown;
    private int currentFoodCountdown;
    private int currentCleanCountdown;
    private int currentEnjoyCountdown;

    public float getTimeCoefficient()
    {
        return timeCoefficient;
    }

    public void setTimeCoefficient(int timeCoefficient)
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
}
