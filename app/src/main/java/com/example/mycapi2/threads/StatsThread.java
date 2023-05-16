package com.example.mycapi2.threads;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.mycapi2.Player;

public class StatsThread extends Thread
{
    private static StatsThread instance;
    private Player player;
    private int statsCoefficient = 1;
    private int statsDownTime = 0;
    private int timeCoefficient = 1;
    private OnUpdateStatsCallback onUpdateStatsCallback;
    private boolean running;

    protected StatsThread()
    {
    }

    public static StatsThread getInstance(@NonNull LifecycleOwner lifecycleOwner)
    {
        if (instance == null)
        {
            instance = new StatsThread();

            lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver()
            {
                @Override
                public void onResume(@NonNull LifecycleOwner owner)
                {
                    if (!instance.running)
                    {
                        instance = instance.reInstance();
                        instance.running = true;
                        instance.start();
                    }
                }

                @Override
                public void onPause(@NonNull LifecycleOwner owner)
                {
                    instance.running = false;
                }
            });
        }

        return instance;
    }

    private StatsThread reInstance()
    {
        StatsThread thread = new StatsThread();
        thread.player = instance.player;
        thread.statsDownTime = instance.statsDownTime;
        thread.onUpdateStatsCallback = instance.onUpdateStatsCallback;

        return thread;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setStatsDownTime(int statsDownTime)
    {
        this.statsDownTime = statsDownTime;
    }

    public void setOnUpdateStatsCallback(OnUpdateStatsCallback callback)
    {
        onUpdateStatsCallback = callback;
    }

    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                int down = (int) ((Math.random()) * 4);
                switch (down)
                {
                    case 0:
                        player.decreaseHealthStats(statsCoefficient);
                        break;
                    case 1:
                        player.decreaseFoodStats(statsCoefficient);
                        break;
                    case 2:
                        player.decreaseCleanStats(statsCoefficient);
                        break;
                    case 3:
                        player.decreaseEnjoyStats(statsCoefficient);
                        break;
                }
                if (onUpdateStatsCallback != null)
                {
                    onUpdateStatsCallback.onUpdate();
                }
                Thread.sleep(statsDownTime * timeCoefficient);
            }
            catch (InterruptedException ignored)
            {
            }
        }
    }

    public interface OnUpdateStatsCallback
    {
        void onUpdate();
    }
}
