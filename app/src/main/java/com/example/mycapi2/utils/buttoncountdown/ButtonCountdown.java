package com.example.mycapi2.utils.buttoncountdown;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.viewmodels.MainViewModel;

public abstract class ButtonCountdown
{
    protected MainViewModel mainViewModel;
    protected TextView countdownText;
    protected Button addButton;

    public ButtonCountdown(ViewModelStoreOwner viewModelStoreOwner)
    {
        mainViewModel = new ViewModelProvider(viewModelStoreOwner).get(MainViewModel.class);
    }

    public void launch(Button addButton, TextView countdownText, int time, int id)
    {
        this.addButton = addButton;
        this.countdownText = countdownText;
        addButton.setVisibility(View.INVISIBLE);
        countdownText.setVisibility(View.VISIBLE);
        countdownText.setText(String.valueOf(mainViewModel.getClickCountdown()));
        startTimer(time);

    }

    protected abstract void startTimer(int time);


    public class CountDownTimer extends android.os.CountDownTimer
    {
        public CountDownTimer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l)
        {
            countdownText.setText(String.valueOf(l / 1000));
        }

        @Override
        public void onFinish()
        {
            addButton.setVisibility(View.VISIBLE);
            countdownText.setVisibility(View.GONE);
        }
    }
}
