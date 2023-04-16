package com.example.mycapi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mycapi2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        MainFragment fragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.rootContainer.getId(), fragment, String.valueOf(false))
                .commit();

    }

    @Override
    protected void onStop() {
        super.onStop();
        ScoreClass.setRunning(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScoreClass.setRunning(true);
        if(ScoreClass.getScore() != 0){
        ScoreClass.startCountScore();}
    }

    @Override
    public void onBackPressed() {

    }
}