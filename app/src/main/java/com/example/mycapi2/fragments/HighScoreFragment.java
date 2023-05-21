package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mycapi2.adapters.HighscoreAdapter;
import com.example.mycapi2.databinding.FragmentHighScoreBinding;
import com.example.mycapi2.databinding.FragmentShopBinding;
import com.example.mycapi2.viewmodels.MainViewModel;

public class HighScoreFragment extends Fragment
{
    private FragmentHighScoreBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentHighScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        MainViewModel mainViewModel = new ViewModelProvider(this).get(
                MainViewModel.class);
        HighscoreAdapter adapter = new HighscoreAdapter(requireContext(), mainViewModel.getAll());
        binding.scoreList.setAdapter(adapter);
        binding.scoreList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
