package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycapi2.R;
import com.example.mycapi2.databinding.FragmentGameBinding;
import com.example.mycapi2.models.Player;
import com.example.mycapi2.threads.ProgressionThread;
import com.example.mycapi2.threads.ScoreThread;
import com.example.mycapi2.threads.StatsThread;
import com.example.mycapi2.utils.buttoncountdown.CleanButtonCountdown;
import com.example.mycapi2.utils.buttoncountdown.EnjoyButtonCountdown;
import com.example.mycapi2.utils.buttoncountdown.FoodButtonCountdown;
import com.example.mycapi2.utils.buttoncountdown.HealthButtonCountdown;
import com.example.mycapi2.viewmodels.MainViewModel;


public class GameFragment extends Fragment
{
    public static final int EASY_MODE = 0;
    public static final int MEDIUM_MODE = 1;
    public static final int HARD_MODE = 2;
    private static final String MODE_KEY = "TIME_KEY";
    private ScoreThread scoreThread;
    private StatsThread statsThread;
    private ProgressionThread progressionThread;
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
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        Player player = mainViewModel.getPlayer();
        scoreThread = ScoreThread.getInstance(requireActivity(), this);
        statsThread = StatsThread.getInstance(requireActivity(), this);
        progressionThread = ProgressionThread.getInstance(requireActivity(), this);


        int mode = getArguments().getInt(MODE_KEY);
        switch (mode)
        {
            case EASY_MODE:
                mainViewModel.setStatsDownTime(3000);
                break;
            case MEDIUM_MODE:
                mainViewModel.setStatsDownTime(1500);
                break;
            case HARD_MODE:
                mainViewModel.setStatsDownTime(500);
                break;
        }

        scoreThread.setOnScoreChangedListener(
                score -> binding.score.post(
                        () ->
                        {
                            binding.score.setText(
                                    getText(R.string.score) + " " + score);
                        }));

        statsThread.setOnUpdateStatsCallback(
                () -> binding.getRoot().post(
                        () ->
                        {
                            binding.healthStats.setText(String.valueOf(player.getHealthStats()));
                            binding.foodStats.setText(String.valueOf(player.getFoodStats()));
                            binding.cleanStats.setText(String.valueOf(player.getCleanStats()));
                            binding.enjoyStats.setText(String.valueOf(player.getEnjoyStats()));
                            setState(player);
                            if (player.getHealthStats() == 0 || player.getFoodStats() == 0 || player.getCleanStats() == 0 || player.getEnjoyStats() == 0)
                            {
                                toGameOver();
                            }
                        }));

        binding.addHealth.setOnClickListener(
                v -> new HealthButtonCountdown(requireActivity()).launch(
                        binding.addHealth, binding.healthCountdown,
                        mainViewModel.getClickCountdown(), 0));
        binding.addFood.setOnClickListener(
                v -> new FoodButtonCountdown(requireActivity()).launch(
                        binding.addFood, binding.foodCountdown, mainViewModel.getClickCountdown(),
                        0));
        binding.addClean.setOnClickListener(
                v -> new CleanButtonCountdown(requireActivity()).launch(
                        binding.addClean, binding.cleanCountdown,
                        mainViewModel.getClickCountdown(), 0));
        binding.addEnjoy.setOnClickListener(
                v -> new EnjoyButtonCountdown(requireActivity()).launch(
                        binding.addEnjoy, binding.enjoyCountdown,
                        mainViewModel.getClickCountdown(), 0));
        binding.shopbtn.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                                                                          .replace(
                                                                                  R.id.rootContainer,
                                                                                  new ShopFragment())
                                                                          .addToBackStack(null)
                                                                          .commit());


    }

    @Override
    public void onStop()
    {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        if (binding.healthCountdown.getText() == "")
        {
            mainViewModel.setCurrentHealthCountdown(0);
        }
        else
        {
            mainViewModel.setCurrentHealthCountdown(
                    Integer.parseInt(binding.healthCountdown.getText().toString()));
        }
        if (binding.foodCountdown.getText() == "")
        {
            mainViewModel.setCurrentFoodCountdown(0);
        }
        else
        {
            mainViewModel.setCurrentFoodCountdown(
                    Integer.parseInt(binding.foodCountdown.getText().toString()));
        }
        if (binding.cleanCountdown.getText() == "")
        {
            mainViewModel.setCurrentCleanCountdown(0);
        }
        else
        {
            mainViewModel.setCurrentCleanCountdown(
                    Integer.parseInt(binding.cleanCountdown.getText().toString()));
        }

        if (binding.enjoyCountdown.getText() == "")
        {
            mainViewModel.setCurrentEnjoyCountdown(0);
        }
        else
        {
            mainViewModel.setCurrentEnjoyCountdown(
                    Integer.parseInt(binding.enjoyCountdown.getText().toString()));
        }
        super.onStop();
    }

    @Override
    public void onResume()
    {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        if (mainViewModel.getCurrentHealthCountdown() != 0)
        {
            new HealthButtonCountdown(requireActivity()).launch(
                    binding.addHealth, binding.healthCountdown,
                    mainViewModel.getCurrentHealthCountdown(), 1);
        }
        if (mainViewModel.getCurrentFoodCountdown() != 0)
        {
            new FoodButtonCountdown(requireActivity()).launch(
                    binding.addFood, binding.foodCountdown,
                    mainViewModel.getCurrentFoodCountdown(), 1);
        }
        if (mainViewModel.getCurrentCleanCountdown() != 0)
        {
            new CleanButtonCountdown(requireActivity()).launch(
                    binding.addClean, binding.cleanCountdown,
                    mainViewModel.getCurrentCleanCountdown(), 1);
        }
        if (mainViewModel.getCurrentEnjoyCountdown() != 0)
        {
            new EnjoyButtonCountdown(requireActivity()).launch(
                    binding.addEnjoy, binding.enjoyCountdown,
                    mainViewModel.getCurrentEnjoyCountdown(), 1);
        }
        super.onResume();
    }

    private void setState(Player player)
    {
        int state = player.getHealthStats() + player.getFoodStats() + player.getCleanStats() + player.getEnjoyStats();
        if ((state / 4) <= 100 && state / 4 > 75)
        {
            binding.state.setImageResource(R.drawable.capi1);
        }
        if ((state / 4) <= 75 && state / 4 > 50)
        {
            binding.state.setImageResource(R.drawable.capi2);
        }
        if ((state / 4) <= 50 && state / 4 > 25)
        {
            binding.state.setImageResource(R.drawable.capi3);
        }
        if ((state / 4) <= 25)
        {
            binding.state.setImageResource(R.drawable.capi4);
        }

    }

    private void toGameOver()
    {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, new GameOverFragment())
                .commit();
    }
}
