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
    public void launch(Button addButton, TextView countdownText)
    {

        super.launch(addButton, countdownText);
        Player player = mainViewModel.getPlayer();
        player.setHealthStats(Math.min(player.getHealthStats() + mainViewModel.getClickPower(), 100));
    }
}
