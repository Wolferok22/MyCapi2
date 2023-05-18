package com.example.mycapi2.threads;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class ProgressionThread extends Thread
{
    private static ProgressionThread instance;
    private boolean running;
    private int timeCoefficient = 1;

    public ProgressionThread(){

    }
    public static ProgressionThread getInstance(@NonNull LifecycleOwner lifecycleOwner)
    {
        if (instance == null)
        {
            instance = new ProgressionThread();

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

    @Override
    public void run()
    {
        while (running){
            try
            {
                Thread.sleep(180*1000);

            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private ProgressionThread reInstance()
    {
        ProgressionThread thread = new ProgressionThread();

        return thread;
    }
}
