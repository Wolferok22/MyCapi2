package com.example.mycapi2.database;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.mycapi2.models.Player;

@Entity public class Data
{
    @Ignore
    private final int timeCoefficient;
    @Ignore
    private final int statsCoefficient;
    @Embedded
    private Player player;
    @ColumnInfo(name = "SCORE")
    private int score;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @Ignore
    public Data(int score, int timeCoefficient, int statsCoefficient, Player player)
    {
        this.score = score;
        this.timeCoefficient = timeCoefficient;
        this.statsCoefficient = statsCoefficient;
        this.player = player;
    }

    public Data()
    {
        timeCoefficient = 0;
        statsCoefficient = 0;
        player = new Player();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
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

    public void setPlayer(Player player)
    {
        this.player = player;
    }
}
