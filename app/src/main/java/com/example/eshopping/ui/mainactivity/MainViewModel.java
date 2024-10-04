package com.example.eshopping.ui.mainactivity;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eshopping.data.model.AuthToken;
import com.example.eshopping.data.model.LoginModel;
import com.example.eshopping.data.model.ProductData;
import com.example.eshopping.data.retrofitclient.RetrofitClient;
import com.example.eshopping.utils.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends ViewModel {
    private MutableLiveData <ApiResponse<List<ProductData>>> productData;

    public LiveData<ApiResponse<List<ProductData>>> getData(){
        if (productData == null) {
            productData = new MutableLiveData<>();
        }
        return productData;
    }
    public void apiData(){
        RetrofitClient.getInstance().getProduct().enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                productData.postValue(ApiResponse.success(response.body()));
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                productData.postValue(ApiResponse.error("Data Not Found"));
            }
        });
    }


}
