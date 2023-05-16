package com.example.mycapi2.threads;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class ScoreThread extends Thread
{
    private static ScoreThread instance;
    private int score = 0;
    private boolean running = false;
    private OnScoreChangedListener onScoreChangedListener;

    protected ScoreThread()
    {
    }

    public static ScoreThread getInstance(LifecycleOwner lifecycleOwner)
    {
        if (instance == null)
        {
            instance = new ScoreThread();

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

    private ScoreThread reInstance()
    {
        ScoreThread thread = new ScoreThread();
        thread.score = instance.score;
        thread.onScoreChangedListener = instance.onScoreChangedListener;

        return thread;
    }

    public void setOnScoreChangedListener(OnScoreChangedListener listener)
    {
        onScoreChangedListener = listener;
    }

    @Override
    public void run()
    {
        running = true;
        while (running)
        {
            score++;
            if (onScoreChangedListener != null)
            {
                onScoreChangedListener.onChanged(score);
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public interface OnScoreChangedListener
    {
        void onChanged(int score);
    }
}
