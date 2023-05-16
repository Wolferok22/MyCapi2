package com.example.mycapi2.fragments;

import static com.example.mycapi2.MainActivity.SCORE_KEY;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycapi2.R;
import com.example.mycapi2.database.DataSave;
import com.example.mycapi2.databinding.FragmentMainBinding;


public class MainFragment extends Fragment
{
    private FragmentMainBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.exitBtn.setOnClickListener(v -> gameExit());
        binding.playBtn.setOnClickListener(v -> gameStart());
        binding.settingsBtn.setOnClickListener(v -> gameSettings());
        int lastScore = requireActivity().getPreferences(Context.MODE_PRIVATE).getInt(SCORE_KEY, 0);
        if (lastScore > 0)
        {
            binding.continueBtn.setVisibility(View.VISIBLE);
        }
        binding.continueBtn.setOnClickListener(v -> gameContinue(lastScore));
    }

    private void gameContinue(int a)
    {
        GameFragment gameFragment = new GameFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.rootContainer, gameFragment, String.valueOf(false))
                .commit();
    }

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