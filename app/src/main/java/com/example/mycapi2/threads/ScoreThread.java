package com.example.mycapi2.threads;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.fragments.MainFragment;
import com.example.mycapi2.viewmodels.MainViewModel;

public class ScoreThread extends Thread
{
    private static ScoreThread instance;
    private boolean running = false;
    private OnScoreChangedListener onScoreChangedListener;
    private MainViewModel mainViewModel;



    protected ScoreThread()
    {
    }

    public static ScoreThread getInstance(ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner)
    {
        if (instance == null)
        {
            instance = new ScoreThread();
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

    private ScoreThread reInstance()
    {
        ScoreThread thread = new ScoreThread();
        thread.onScoreChangedListener = instance.onScoreChangedListener;
        thread.mainViewModel = instance.mainViewModel;

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
            mainViewModel.setScore(mainViewModel.getScore()+1);
            if (onScoreChangedListener != null)
            {
                onScoreChangedListener.onChanged(mainViewModel.getScore());
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
