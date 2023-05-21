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
import com.example.mycapi2.database.ShopProductsRepo;
import com.example.mycapi2.databinding.FragmentMainBinding;
import com.example.mycapi2.models.Player;
import com.example.mycapi2.threads.ProgressionThread;
import com.example.mycapi2.threads.ScoreThread;
import com.example.mycapi2.threads.StatsThread;
import com.example.mycapi2.viewmodels.MainViewModel;


public class MainFragment extends Fragment
{
    private FragmentMainBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ShopProductsRepo.getInstance().init();
        ScoreThread.setInstance(null);
        StatsThread.setInstance(null);
        ProgressionThread.setInstance(null);
        requireActivity().getViewModelStore().clear();
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        mainViewModel.setPlayer(new Player());
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        binding.exitBtn.setOnClickListener(v -> gameExit());
        binding.playBtn.setOnClickListener(v -> gameStart());
        binding.settingsBtn.setOnClickListener(v -> gameSettings());
        binding.highscoreBtn.setOnClickListener(v -> highScores());
        /*int lastScore = requireActivity().getPreferences(Context.MODE_PRIVATE).getInt(SCORE_KEY, 0);
        if (lastScore > 0)
        {
            binding.continueBtn.setVisibility(View.VISIBLE);
        }
        binding.continueBtn.setOnClickListener(v -> gameContinue(lastScore));*/

    }

    private void highScores()
    {
        HighScoreFragment highScoreFragment = new HighScoreFragment();
        getParentFragmentManager().beginTransaction()
                                  .addToBackStack(null)
                                  .replace(R.id.rootContainer, highScoreFragment)
                                  .commit();
    }

    /*private void gameContinue(int a)
    {
        GameFragment gameFragment = new GameFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, gameFragment, String.valueOf(false))
                .commit();

    }*/

    private void gameSettings()
    {
        SettingsFragment settingsFragment = new SettingsFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, settingsFragment, String.valueOf(false))
                .addToBackStack(null)
                .commit();
    }

    private void gameStart()
    {
        DifficultChoiceFragment fragment = new DifficultChoiceFragment();
        fragment.show(getParentFragmentManager(), null);
    }

    private void gameExit()
    {
        requireActivity().finish();
    }


}