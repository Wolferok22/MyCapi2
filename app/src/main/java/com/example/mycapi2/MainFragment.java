package com.example.mycapi2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycapi2.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {


    private FragmentMainBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        return binding.getRoot();



        //return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.exit.setOnClickListener(v -> gameExit());
        binding.play.setOnClickListener(v -> gameStart());
        binding.settings.setOnClickListener(v -> gameSettings());
    }

    private void gameSettings() {
        SettingsFragment settingsFragment = new SettingsFragment();
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.rootContainer, settingsFragment, String.valueOf(false))
                .commit();
    }

    private void gameStart() {
        GameFragment gameFragment = new GameFragment();
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.rootContainer, gameFragment, String.valueOf(false))
                .commit();
        ScoreClass.setScore(0);
        ScoreClass.startCountScore();
        ScoreClass.setRunning(true);
    }

    private void gameExit() {
        requireActivity().finish();
    }


}