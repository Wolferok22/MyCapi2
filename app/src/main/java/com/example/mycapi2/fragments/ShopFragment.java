package com.example.mycapi2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mycapi2.adapters.ShopProductsAdapter;
import com.example.mycapi2.databinding.FragmentShopBinding;
import com.example.mycapi2.models.ShopProduct;
import com.example.mycapi2.viewmodels.MainViewModel;

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
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(
                MainViewModel.class);
        ShopProductsAdapter adapter = new ShopProductsAdapter(requireContext());
        binding.shopList.setAdapter(adapter);
        binding.shopList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnBuyClickListener(new ShopProductsAdapter.OnBuyClickListener()
        {
            @Override
            public void onBuy(ShopProduct shopProduct)
            {
                if (mainViewModel.getScore() >= shopProduct.getPrice())
                {
                    mainViewModel.setScore(mainViewModel.getScore() - shopProduct.getPrice() );
                    shopProduct.setLevel(shopProduct.getLevel() + 1);
                    shopProduct.setPrice((int) (100 * Math.pow(2.2, shopProduct.getLevel())));
                    switch (shopProduct.getId()){
                        case 0:
                            mainViewModel.incrementClickPower();
                            break;
                        case 1:
                            mainViewModel.decrementClickCountdown();
                            break;
                    }

                }
                else {
                    Toast.makeText(requireContext(),"У вас недостаточно очков", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
