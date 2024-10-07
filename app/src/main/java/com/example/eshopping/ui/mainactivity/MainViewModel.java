package com.example.eshopping.ui.mainactivity;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eshopping.data.model.AuthToken;
import com.example.eshopping.data.model.LoginModel;
import com.example.eshopping.data.model.ProductData;
import com.example.eshopping.data.repo.HomeRepo;
import com.example.eshopping.data.retrofitclient.RetrofitClient;
import com.example.eshopping.utils.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends ViewModel {
    HomeRepo homeRepo = new HomeRepo();
    public LiveData<ApiResponse<List<ProductData>>> getData = homeRepo.getData();

    public void apiData(){

        homeRepo.getProducts();
    }
}
