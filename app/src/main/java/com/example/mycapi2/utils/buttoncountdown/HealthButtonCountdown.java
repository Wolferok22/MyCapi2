package com.example.mycapi2.utils.buttoncountdown;

import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.models.Player;

public class HealthButtonCountdown extends ButtonCountdown
{

    public HealthButtonCountdown(ViewModelStoreOwner viewModelStoreOwner)
    {
        super(viewModelStoreOwner);
    }

    @Override
    public void launch(Button addButton, TextView countdownText, int time, int id)
    {
        super.launch(addButton, countdownText, time, id);
        switch (id)
        {
            case 0:
                Player player = mainViewModel.getPlayer();
                player.setHealthStats(
                        Math.min(player.getHealthStats() + mainViewModel.getClickPower(), 100));
                break;
            case 1:
                break;
        }

    }

    @Override
    protected void startTimer(int time)
    {
        new ButtonCountdown.CountDownTimer(time * 1000L, 1000)
        {

            @Override
            public void onTick(long l)
            {
                super.onTick(l);
            }
        }.start();
    }


}
