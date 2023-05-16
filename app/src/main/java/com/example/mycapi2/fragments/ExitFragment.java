package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mycapi2.R;
import com.example.mycapi2.threads.ScoreThread;
import com.example.mycapi2.databinding.FragmentExitBinding;


public class ExitFragment extends DialogFragment
{

    FragmentExitBinding binding;

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
        binding.noExitFragment.setOnClickListener(view1 ->
                                                  {
                                                      dismiss();
                                                  });
        binding.yesExitFragment.setOnClickListener(view1 -> exitGame());
        super.onViewCreated(view, savedInstanceState);
    }

    private void exitGame()
    {
        MainFragment mainFragment = new MainFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, mainFragment, String.valueOf(false))
                .commit();
        dismiss();
    }
}