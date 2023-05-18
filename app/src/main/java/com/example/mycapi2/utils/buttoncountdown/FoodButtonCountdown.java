package com.example.mycapi2.utils.buttoncountdown;

import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mycapi2.models.Player;

public class FoodButtonCountdown extends ButtonCountdown
{
    public FoodButtonCountdown(ViewModelStoreOwner viewModelStoreOwner)
    {
        super(viewModelStoreOwner);
    }

    @Override
    public void launch(Button addButton, TextView countdownText)
    {
        super.launch(addButton, countdownText);
        Player player = mainViewModel.getPlayer();
        player.setFoodStats(Math.min(player.getFoodStats() + mainViewModel.getClickPower(), 100));
    }
}
