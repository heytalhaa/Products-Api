package com.example.eshopping.ui.mainactivity.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.R;
import com.example.eshopping.data.model.ProductData;
import com.example.eshopping.data.retrofitclient.RetrofitClient;
import com.example.eshopping.databinding.FragmentDetailsBinding;
import com.example.eshopping.ui.detailsactivity.DetailsActivity;
import com.example.eshopping.ui.mainactivity.ImagesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(getLayoutInflater());
        return (binding.getRoot());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setting the navigation on details invisible
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigationV);
        if (bottomNavigationView != null){
            bottomNavigationView.setVisibility(View.INVISIBLE);
        }

        // getting data from home fragment to details fragment
        int position = getArguments().getInt("id",  0);
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

                    // setting image adapter in image recyclerview
                    ImagesAdapter imagesAdapter = new ImagesAdapter(getContext(), data.getImages());
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

    //when details fragment destroy navigation set Visible
    @Override
    public void onDestroy() {
        super.onDestroy();
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigationV);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
}