package com.example.mycapi2.models;

public class ShopProduct
{
    private String title;
    private int level;
    private int price;

    public ShopProduct(String title, int level, int price)
    {
        this.title = title;
        this.level = level;
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public int getLevel()
    {
        return level;
    }

    public int getPrice()
    {
        return price;
    }
}
