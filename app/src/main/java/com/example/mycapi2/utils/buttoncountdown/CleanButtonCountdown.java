package com.example.mycapi2.utils.buttoncountdown;

import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.models.Player;
import com.example.mycapi2.viewmodels.MainViewModel;

public class CleanButtonCountdown extends ButtonCountdown
{
    public CleanButtonCountdown(ViewModelStoreOwner viewModelStoreOwner)
    {
        super(viewModelStoreOwner);
    }

    @Override
    public void launch(Button addButton, TextView countdownText)
    {
        super.launch(addButton, countdownText);
        Player player = mainViewModel.getPlayer();
        player.setCleanStats(Math.min(player.getCleanStats() + mainViewModel.getClickPower(), 100));
    }
    @Override
    protected void startTimer()
    {
        new ButtonCountdown.CountDownTimer(mainViewModel.getClickCountdown() * 1000L, 1000)
        {
            @Override
            public void onTick(long l)
            {
                super.onTick(l);
                mainViewModel.incrementCurrentCleanCountdown();
            }
        }.start();
    }
}
