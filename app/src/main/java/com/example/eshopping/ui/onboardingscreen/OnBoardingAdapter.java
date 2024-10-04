package com.example.eshopping.ui.onboardingscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshopping.R;
import com.example.eshopping.data.model.OnBoardingData;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.MyViewHolder> {
    private Context context;
    private List<OnBoardingData> list;

    public OnBoardingAdapter (Context context, List<OnBoardingData> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.onboarding_items, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OnBoardingData data = list.get(position);
            holder.itemImg.setImageResource(data.getImage());
            holder.title.setText(data.getTitle());
            holder.details.setText(data.getDetails());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImg;
        TextView title, details;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemIV);
            title = itemView.findViewById(R.id.titleTV);
            details = itemView.findViewById(R.id.detailsTV);
        }
    }
}
