package com.example.mycapi2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycapi2.R;
import com.example.mycapi2.database.ShopProductsRepo;
import com.example.mycapi2.databinding.ItemShopProductBinding;
import com.example.mycapi2.models.ShopProduct;

public class ShopProductsAdapter extends RecyclerView.Adapter<ShopProductsAdapter.ShopProductViewHolder>
{
    private ShopProductsRepo shopProductsRepo = ShopProductsRepo.getInstance();

    @NonNull
    @Override
    public ShopProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_shop_product, parent, false);

        return new ShopProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShopProductViewHolder holder, int position)
    {
        ShopProduct shopProduct = shopProductsRepo.getOne(position);
        holder.binding.title.setText(shopProduct.getTitle());
        holder.binding.level.setText(holder.binding.level.getText() +" "+ shopProduct.getLevel());
        holder.binding.price.setText(holder.binding.price.getText() +" "+ shopProduct.getPrice());
    }

    @Override
    public int getItemCount()
    {
        return shopProductsRepo.getLength();
    }

    protected class ShopProductViewHolder extends RecyclerView.ViewHolder
    {
        private ItemShopProductBinding binding;

        public ShopProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            binding = ItemShopProductBinding.bind(itemView);
        }
    }

}
