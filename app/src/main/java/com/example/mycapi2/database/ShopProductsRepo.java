package com.example.mycapi2.database;

import android.content.res.Resources;

import com.example.mycapi2.R;
import com.example.mycapi2.models.ShopProduct;

import java.util.ArrayList;
import java.util.List;

public class ShopProductsRepo
{
    private static ShopProductsRepo instance;
    private final List<ShopProduct> shopProducts = new ArrayList<>();
    private Resources resources;

    protected ShopProductsRepo()
    {

    }

    public static ShopProductsRepo getInstance()
    {
        if (instance == null)
        {
            instance = new ShopProductsRepo();
        }
        return instance;
    }

    public void init()
    {
        shopProducts.add(
                new ShopProduct(resources.getText(
                        R.string.increase_add_stats).toString(), 0, 100));
        shopProducts.add(
                new ShopProduct(resources.getText(
                        R.string.decrease_countdown_time).toString(), 0, 100));
    }

    public ShopProduct getOne(int position)
    {
        return shopProducts.get(position);
    }

    public int getLength()
    {
        return shopProducts.size();
    }

    public void setResources(Resources resources)
    {
        this.resources = resources;
    }

}
