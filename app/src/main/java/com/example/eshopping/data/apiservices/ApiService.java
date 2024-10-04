package com.example.eshopping.data.apiservices;

import com.example.eshopping.data.model.ProductData;
import com.example.eshopping.data.model.AuthToken;
import com.example.eshopping.data.model.CreateUser;
import com.example.eshopping.data.model.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/v1/auth/login")
    Call<AuthToken> verifyLoginUser(@Body LoginModel loginModel);

    @POST("api/v1/users/")
    Call<CreateUser> createNewUser (@Body CreateUser createUser);

    @GET("api/v1/products/")
    Call<List<ProductData>> getProduct();

    @GET("api/v1/products/{id}")
    Call<ProductData> getProductDetails(@Path("id") int id);
}
