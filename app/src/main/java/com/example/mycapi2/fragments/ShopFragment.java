package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mycapi2.adapters.ShopProductsAdapter;
import com.example.mycapi2.databinding.FragmentShopBinding;

public class ShopFragment extends Fragment
{
    private FragmentShopBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentShopBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        binding.shopList.setAdapter(new ShopProductsAdapter());
        binding.shopList.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}