package com.example.mycapi2.adapters;

import android.content.Context;
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
    private final Context context;
    private ShopProductsRepo shopProductsRepo = ShopProductsRepo.getInstance();
    private OnBuyClickListener onBuyClickListener;

    public ShopProductsAdapter(Context context)
    {
        this.context = context;
    }

    public void setOnBuyClickListener(OnBuyClickListener onBuyClickListener)
    {
        this.onBuyClickListener = onBuyClickListener;
    }

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
        holder.binding.level.setText(
                context.getText(R.string.current_level) + " " + shopProduct.getLevel());
        holder.binding.price.setText(
                context.getText(R.string.price) + " " + shopProduct.getPrice());
        holder.binding.btnUp.setOnClickListener(view ->
                                                {
                                                    onBuyClickListener.onBuy(shopProduct);
                                                    notifyItemChanged(position);

                                                });
    }

    @Override
    public int getItemCount()
    {
        return shopProductsRepo.getLength();
    }

    public interface OnBuyClickListener
    {
        void onBuy(ShopProduct shopProduct);
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
