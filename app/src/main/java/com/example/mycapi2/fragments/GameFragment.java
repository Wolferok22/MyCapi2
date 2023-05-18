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
        scoreThread = ScoreThread.getInstance(requireActivity(),this);
        statsThread = StatsThread.getInstance(requireActivity(), this);
        progressionThread = ProgressionThread.getInstance(this);

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

        binding.addHealth.setOnClickListener(
                v -> new HealthButtonCountdown(requireActivity()).launch(
                        binding.addHealth, binding.healthCountdown));
        binding.addFood.setOnClickListener(
                v -> new FoodButtonCountdown(requireActivity()).launch(
                        binding.addFood, binding.foodCountdown));
        binding.addClean.setOnClickListener(
                v -> new CleanButtonCountdown(requireActivity()).launch(
                        binding.addClean, binding.cleanCountdown));
        binding.addEnjoy.setOnClickListener(
                v -> new EnjoyButtonCountdown(requireActivity()).launch(
                        binding.addEnjoy, binding.enjoyCountdown));
        binding.shopbtn.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                                                                          .replace(
                                                                                  R.id.rootContainer,
                                                                                  new ShopFragment())
                                                                          .addToBackStack(null)
                                                                          .commit());

    }


}
