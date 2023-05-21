package com.example.mycapi2.threads;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.viewmodels.MainViewModel;

public class ProgressionThread extends Thread
{
    private static ProgressionThread instance;
    private boolean running;
    private MainViewModel mainViewModel;


    public ProgressionThread()
    {

    }


    public static void setInstance(ProgressionThread instance)
    {
        ProgressionThread.instance = instance;
    }

    public static ProgressionThread getInstance(ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner)
    {
        if (instance == null)
        {
            instance = new ProgressionThread();
            ;
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

    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                Thread.sleep(180 * 1000);
                mainViewModel.setTimeCoefficient(mainViewModel.getTimeCoefficient() - 0.05F);
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
        thread.mainViewModel = mainViewModel;
        return thread;
    }
}
