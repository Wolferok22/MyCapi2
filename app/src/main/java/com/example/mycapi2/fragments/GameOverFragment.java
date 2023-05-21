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
import com.example.mycapi2.database.save.Data;
import com.example.mycapi2.databinding.FragmentGameOverBinding;
import com.example.mycapi2.viewmodels.MainViewModel;

public class GameOverFragment extends Fragment
{
    private FragmentGameOverBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        binding = FragmentGameOverBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        binding.score.setText(getText(R.string.score)+" "+ mainViewModel.getScore());
        binding.close.setOnClickListener(v -> closeTotal());
        super.onViewCreated(view, savedInstanceState);
    }

    private void closeTotal()
    {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        mainViewModel.insert(new Data(mainViewModel.getScore()));
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, new MainFragment())
                .commit();
    }
}
