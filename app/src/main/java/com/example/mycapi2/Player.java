package com.example.mycapi2;

public class Player
{

    private int healthStats = 100;
    private int foodStats = 100;
    private int cleanStats = 100;
    private int enjoyStats = 100;
    private int addStats = 10;
    private int countdownTime = 40;

    public Player(int healthStats, int foodStats, int cleanStats, int enjoyStats, int addStats, int countdownTime)
    {
        this.healthStats = healthStats;
        this.foodStats = foodStats;
        this.cleanStats = cleanStats;
        this.enjoyStats = enjoyStats;
        this.addStats = addStats;
        this.countdownTime = countdownTime;
    }

    public Player()
    {

    }

    public int getHealthStats()
    {
        return healthStats;
    }

    public void setHealthStats(int healthStats)
    {
        this.healthStats = healthStats;
    }

    public void decreaseHealthStats(int healthStats)
    {
        this.healthStats -= healthStats;
    }

    public int getFoodStats()
    {
        return foodStats;
    }

    public void setFoodStats(int foodStats)
    {
        this.foodStats = foodStats;
    }

    public void decreaseFoodStats(int foodStats)
    {
        this.foodStats -= foodStats;
    }

    public int getCleanStats()
    {
        return cleanStats;
    }

    public void setCleanStats(int cleanStats)
    {
        this.cleanStats = cleanStats;
    }

    public void decreaseCleanStats(int cleanStats)
    {
        this.cleanStats -= cleanStats;
    }

    public int getEnjoyStats()
    {
        return enjoyStats;
    }

    public void setEnjoyStats(int enjoyStats)
    {
        this.enjoyStats = enjoyStats;
    }

    public void decreaseEnjoyStats(int enjoyStats)
    {
        this.enjoyStats -= enjoyStats;
    }

    public int getAddStats()
    {
        return addStats;
    }

    public void setAddStats(int addStats)
    {
        this.addStats = addStats;
    }

    public int getCountdownTime()
    {
        return countdownTime;
    }

    public void setCountdownTime(int countdownTime)
    {
        this.countdownTime = countdownTime;
    }
}
