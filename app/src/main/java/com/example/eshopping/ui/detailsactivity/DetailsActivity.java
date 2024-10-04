package com.example.eshopping.ui.detailsactivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.eshopping.R;
import com.example.eshopping.data.model.ProductData;
import com.example.eshopping.data.retrofitclient.RetrofitClient;
import com.example.eshopping.databinding.ActivityDetailsBinding;
import com.example.eshopping.ui.mainactivity.ImagesAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBar.toolBar);

        int position = getIntent().getIntExtra("id", 0);
        if (position!=0){
            RetrofitClient.getInstance().getProductDetails(position).enqueue(new Callback<ProductData>() {
                @Override
                public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                    ProductData data = response.body();
                    binding.pID.setText(String.valueOf(data.getid()));
                    binding.pTitle.setText(data.getTitle());
                    binding.pCategory.setText(data.getCategory().getName());
                    binding.pPrice.setText(String.valueOf(data.getPrice()));
                    binding.pDescription.setText(data.getDescription());

                    ImagesAdapter imagesAdapter = new ImagesAdapter(DetailsActivity.this, data.getImages());
                    binding.imgV.setAdapter(imagesAdapter);
//                     here is the code for carousal item
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (binding.imgV.getCurrentItem()+1<imagesAdapter.getItemCount()){
                    binding.imgV.setCurrentItem(binding.imgV.getCurrentItem()+1);
                }else {
                    binding.imgV.setCurrentItem(0);
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
//                     carousal code end here
//                    Glide.with(DetailsActivity.this)
//                            .load(data.getImages().get(0))
//                            .placeholder(R.drawable.loading)
//                            .into(binding.imgV);
                }

                @Override
                public void onFailure(Call<ProductData> call, Throwable t) {

                }
            });
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}