package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycapi2.Player;
import com.example.mycapi2.R;
import com.example.mycapi2.databinding.FragmentGameBinding;
import com.example.mycapi2.threads.ScoreThread;
import com.example.mycapi2.threads.StatsThread;


public class GameFragment extends Fragment
{
    public static final int EASY_MODE = 0;
    public static final int MEDIUM_MODE = 1;
    public static final int HARD_MODE = 2;
    private static final String MODE_KEY = "TIME_KEY";
    private final Player player = new Player();
    private ScoreThread scoreThread;
    private StatsThread statsThread;
    private int statsDownTime = 3000;
    private FragmentGameBinding binding;

    public static GameFragment newInstance(int mode)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(MODE_KEY, mode);
        GameFragment gameFragment = new GameFragment();
        gameFragment.setArguments(bundle);
        return gameFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        scoreThread = ScoreThread.getInstance(this);
        statsThread = StatsThread.getInstance(this);

        int mode = getArguments().getInt(MODE_KEY);
        switch (mode)
        {
            case EASY_MODE:
                statsDownTime = 3000;
                break;
            case MEDIUM_MODE:
                statsDownTime = 1500;
                break;
            case HARD_MODE:
                statsDownTime = 500;
                break;
        }

        scoreThread.setOnScoreChangedListener(
                score -> binding.score.post(
                        () ->
                        {
                            binding.score.setText(
                                    getText(R.string.score) + " " + score);
                        }));
        scoreThread.start();

        statsThread.setPlayer(player);
        statsThread.setStatsDownTime(statsDownTime);
        statsThread.setOnUpdateStatsCallback(
                () -> binding.getRoot().post(
                        () ->
                        {
                            binding.healthStats.setText(String.valueOf(player.getHealthStats()));
                            binding.foodStats.setText(String.valueOf(player.getFoodStats()));
                            binding.cleanStats.setText(String.valueOf(player.getCleanStats()));
                            binding.enjoyStats.setText(String.valueOf(player.getEnjoyStats()));
                        }));
        statsThread.start();

        binding.addHealth.setOnClickListener(v -> healthButtonCountdown(player.getCountdownTime()));
        binding.addFood.setOnClickListener(v -> foodButtonCountdown(player.getCountdownTime()));
        binding.addClean.setOnClickListener(v -> cleanButtonCountdown(player.getCountdownTime()));
        binding.addEnjoy.setOnClickListener(v -> enjoyButtonCountdown(player.getCountdownTime()));
    }

    private void healthButtonCountdown(int time)
    {
        player.setHealthStats(Math.min(player.getHealthStats() + player.getAddStats(), 100));
        binding.addHealth.setVisibility(View.INVISIBLE);
        binding.healthCountdown.setVisibility(View.VISIBLE);
        binding.healthCountdown.setText(String.valueOf(player.getCountdownTime()));
        new CountDownTimer(time * 1000L, 1000)
        {
            @Override
            public void onTick(long l)
            {
                binding.healthCountdown.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish()
            {
                binding.addHealth.setVisibility(View.VISIBLE);
                binding.healthCountdown.setVisibility(View.GONE);
            }
        }.start();
    }

    private void foodButtonCountdown(int time)
    {
        player.setFoodStats(Math.min(player.getFoodStats() + player.getAddStats(), 100));
        binding.addFood.setVisibility(View.INVISIBLE);
        binding.foodCountdown.setVisibility(View.VISIBLE);
        binding.foodCountdown.setText(String.valueOf(player.getCountdownTime()));
        new CountDownTimer(time * 1000L, 1000)
        {

            @Override
            public void onTick(long l)
            {
                binding.foodCountdown.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish()
            {
                binding.addFood.setVisibility(View.VISIBLE);
                binding.foodCountdown.setVisibility(View.GONE);

            }
        }.start();
    }

    private void cleanButtonCountdown(int time)
    {
        player.setCleanStats(Math.min(player.getCleanStats() + player.getAddStats(), 100));
        binding.addClean.setVisibility(View.INVISIBLE);
        binding.cleanCountdown.setVisibility(View.VISIBLE);
        binding.cleanCountdown.setText(String.valueOf(player.getCountdownTime()));
        new CountDownTimer(time * 1000L, 1000)
        {

            @Override
            public void onTick(long l)
            {
                binding.cleanCountdown.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish()
            {
                binding.addClean.setVisibility(View.VISIBLE);
                binding.cleanCountdown.setVisibility(View.GONE);
            }
        }.start();
    }

    private void enjoyButtonCountdown(int time)
    {
        player.setEnjoyStats(Math.min(player.getEnjoyStats() + player.getAddStats(), 100));
        binding.addEnjoy.setVisibility(View.INVISIBLE);
        binding.enjoyCountdown.setVisibility(View.VISIBLE);
        binding.enjoyCountdown.setText(String.valueOf(player.getCountdownTime()));
        new CountDownTimer(time * 1000L, 1000)
        {

            @Override
            public void onTick(long l)
            {
                binding.enjoyCountdown.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish()
            {
                binding.addEnjoy.setVisibility(View.VISIBLE);
                binding.enjoyCountdown.setVisibility(View.GONE);
            }
        }.start();
    }
}