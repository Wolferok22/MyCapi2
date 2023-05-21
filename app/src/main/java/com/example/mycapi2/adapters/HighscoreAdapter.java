package com.example.mycapi2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycapi2.R;
import com.example.mycapi2.database.save.Data;
import com.example.mycapi2.databinding.ItemHighScoreBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder>
{
    private final Context context;

    private List<Data> dataList = new ArrayList<>();

    public HighscoreAdapter(Context context, List<Data> dataList)
    {
        this.context = context;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        this.dataList.sort((data, t1) -> Integer.compare(t1.getScore(), data.getScore()));

    }

    @NonNull
    public HighscoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_high_score, parent, false);
        return new HighscoreAdapter.HighscoreViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HighscoreViewHolder holder, int position)
    {
        Data data = dataList.get(position);
        holder.binding.highScore.setText(String.valueOf(data.getScore()));
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }


    protected class HighscoreViewHolder extends RecyclerView.ViewHolder
    {
        private ItemHighScoreBinding binding;

        public HighscoreViewHolder(@NonNull View itemView)
        {
            super(itemView);
            binding = ItemHighScoreBinding.bind(itemView);
        }
    }

}
