package com.example.mycapi2.models;

public class ShopProduct
{
    private String title;
    private int level;
    private int price;
    private final int id;

    public ShopProduct(String title, int level, int price, int id)
    {
        this.title = title;
        this.level = level;
        this.price = price;
        this.id = id;
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

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getId()
    {
        return id;
    }
}
