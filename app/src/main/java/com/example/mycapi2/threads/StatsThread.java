package com.example.mycapi2.threads;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.models.Player;
import com.example.mycapi2.viewmodels.MainViewModel;

public class StatsThread extends Thread
{
    private static StatsThread instance;
    private int statsCoefficient = 1;
    private MainViewModel mainViewModel;
    private OnUpdateStatsCallback onUpdateStatsCallback;
    private boolean running;

    protected StatsThread()
    {

    }

    public static void setInstance(StatsThread instance)
    {
        StatsThread.instance = instance;
    }

    public static StatsThread getInstance(ViewModelStoreOwner viewModelStoreOwner, @NonNull LifecycleOwner lifecycleOwner)
    {
        if (instance == null)
        {
            instance = new StatsThread();
            instance.mainViewModel = new ViewModelProvider(viewModelStoreOwner).get(
                    MainViewModel.class);

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
        thread.onUpdateStatsCallback = instance.onUpdateStatsCallback;
        thread.mainViewModel = instance.mainViewModel;
        return thread;
    }


    public void setOnUpdateStatsCallback(OnUpdateStatsCallback callback)
    {
        onUpdateStatsCallback = callback;
    }

    @Override
    public void run()
    {
        Player player = mainViewModel.getPlayer();
        if (player == null)
        {
            return;
        }
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
                Thread.sleep(
                        (long) (mainViewModel.getStatsDownTime() * mainViewModel.getTimeCoefficient()));
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
