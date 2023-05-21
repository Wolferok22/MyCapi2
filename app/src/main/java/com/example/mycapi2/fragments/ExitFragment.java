package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycapi2.R;
import com.example.mycapi2.database.save.Data;
import com.example.mycapi2.database.save.PlayerPreferences;
import com.example.mycapi2.threads.ScoreThread;
import com.example.mycapi2.databinding.FragmentExitBinding;
import com.example.mycapi2.viewmodels.MainViewModel;


public class ExitFragment extends DialogFragment
{

    private FragmentExitBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentExitBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        binding.noExitFragment.setOnClickListener(v ->
                                                  {
                                                      dismiss();
                                                  });
        binding.yesExitFragment.setOnClickListener(v -> finishGame());
        super.onViewCreated(view, savedInstanceState);
    }

    private void finishGame()
    {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        mainViewModel.insert(new Data(mainViewModel.getScore()));
        MainFragment mainFragment = new MainFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, mainFragment, String.valueOf(false))
                .commit();
        dismiss();

    }


}