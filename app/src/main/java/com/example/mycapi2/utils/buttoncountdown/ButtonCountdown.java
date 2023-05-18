package com.example.mycapi2.utils.buttoncountdown;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.viewmodels.MainViewModel;

public abstract class ButtonCountdown
{
    protected MainViewModel mainViewModel;

    public ButtonCountdown(ViewModelStoreOwner viewModelStoreOwner)
    {
        mainViewModel = new ViewModelProvider(viewModelStoreOwner).get(MainViewModel.class);
    }

    public void launch(Button addButton, TextView countdownText)
    {
        addButton.setVisibility(View.INVISIBLE);
        countdownText.setVisibility(View.VISIBLE);
        countdownText.setText(String.valueOf(mainViewModel.getClickCountdown()));
        new CountDownTimer(mainViewModel.getClickCountdown() * 1000L, 1000)
        {
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
        }.start();
    }
    public int getCountdownTime(TextView countdownText){
        return Integer.parseInt(countdownText.getText().toString());
    }
}
