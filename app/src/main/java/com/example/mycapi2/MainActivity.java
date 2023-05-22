package com.example.mycapi2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycapi2.database.ShopProductsRepo;
import com.example.mycapi2.databinding.ActivityMainBinding;
import com.example.mycapi2.fragments.ExitFragment;
import com.example.mycapi2.fragments.GameFragment;
import com.example.mycapi2.fragments.MainFragment;
import com.example.mycapi2.fragments.ShopFragment;
import com.example.mycapi2.models.Player;
import com.example.mycapi2.threads.ScoreThread;
import com.example.mycapi2.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity
{

    public static final String SCORE_KEY = "SCORE_KEY";
    FragmentManager manager = getSupportFragmentManager();
    ExitFragment exitFragment = new ExitFragment();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.rootContainer.getId(), fragment, String.valueOf(false))
                .commit();
        ShopProductsRepo.getInstance().setResources(getResources());
    }

    @Override
    public void onBackPressed()
    {

        if (getSupportFragmentManager().findFragmentById(
                binding.rootContainer.getId()) instanceof GameFragment)
        {
            exitFragment.show(manager, null);
        }
        else
        {
            super.onBackPressed();
        }
    }

}