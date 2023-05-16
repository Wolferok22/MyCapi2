package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mycapi2.R;
import com.example.mycapi2.databinding.FragmentDifficultChoiceBinding;

public class DifficultChoiceFragment extends DialogFragment
{

    FragmentDifficultChoiceBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentDifficultChoiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        binding.easyBtn.setOnClickListener(v -> toGameFragment(GameFragment.EASY_MODE));
        binding.normalBtn.setOnClickListener(v -> toGameFragment(GameFragment.MEDIUM_MODE));
        binding.hardBtn.setOnClickListener(v -> toGameFragment(GameFragment.HARD_MODE));
    }

    private void toGameFragment(int time)
    {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, GameFragment.newInstance(time))
                .commit();
        dismiss();
    }


}
