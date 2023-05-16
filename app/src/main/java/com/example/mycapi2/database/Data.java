package com.example.mycapi2.database;

import com.example.mycapi2.Player;

public class Data
{
    private final int score;
    private final int timeCoefficient;
    private final int statsCoefficient;
    private final Player player;

    public Data(int score, int timeCoefficient, int statsCoefficient, Player player)
    {
        this.score = score;
        this.timeCoefficient = timeCoefficient;
        this.statsCoefficient = statsCoefficient;
        this.player = player;
    }

    public int getScore()
    {
        return score;
    }

    public int getTimeCoefficient()
    {
        return timeCoefficient;
    }

    public int getStatsCoefficient()
    {
        return statsCoefficient;
    }

    public Player getPlayer()
    {
        return player;
    }
}
