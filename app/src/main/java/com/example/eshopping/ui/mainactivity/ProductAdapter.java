package com.example.eshopping.ui.mainactivity;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.eshopping.R;
import com.example.eshopping.data.model.ProductData;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<ProductData> productData;
    private OnProductListener onProductListener;

    public ProductAdapter(Context context, List<ProductData> productData){
        this.context = context;
        this.productData = productData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_items, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductData data = productData.get(position);
        holder.pID.setText(String.valueOf(data.getid()));
        holder.pTitle.setText(data.getTitle());
        holder.pCategory.setText(String.valueOf(data.getCategory().getName()));
        holder.pPrice.setText(String.valueOf(data.getPrice()));

//        Glide.with(context)
//                .load(data.getImages().get(0))
//                .placeholder(R.drawable.loading)
//                .into(holder.imageRV);

        ImagesAdapter imagesAdapter = new ImagesAdapter(context, data.getImages());
        holder.imageRV.setAdapter(imagesAdapter);

        holder.detailsView.setOnClickListener(view -> {
            onProductListener.onProductClicked((int) data.getid());
        });

        // here is the code for carousal item
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (holder.imageRV.getCurrentItem()+1<imagesAdapter.getItemCount()){
                    holder.imageRV.setCurrentItem(holder.imageRV.getCurrentItem()+1);
                }else {
                   holder.imageRV.setCurrentItem(0);
                }
                handler.postDelayed(this, 4000);
            }
        };
        handler.postDelayed(runnable, 4000);
//         carousal code end here

    }

    @Override
    public int getItemCount() {
        return productData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageRV;
        ViewPager2 imageRV;
//        RecyclerView imageRV;
        TextView pID, pTitle , pCategory, pPrice, pRating, pCounting;
        MaterialCardView detailsView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pID = itemView.findViewById(R.id.pID);
            pTitle = itemView.findViewById(R.id.pTitle);
            pCategory = itemView.findViewById(R.id.pCategory);
            pPrice = itemView.findViewById(R.id.pPrice);
            imageRV = itemView.findViewById(R.id.imgV);
            detailsView = itemView.findViewById(R.id.pItemView);

        }
    }
    public interface OnProductListener{
       void onProductClicked(int position);
    }
    public void OnProductListener(OnProductListener onProductListener){
        this.onProductListener = onProductListener;
    }
}
