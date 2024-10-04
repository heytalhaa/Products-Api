package com.example.eshopping.ui.loginui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eshopping.data.model.AuthToken;
import com.example.eshopping.data.model.LoginModel;
import com.example.eshopping.data.retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<AuthToken> loginModel = new MutableLiveData<>();

    public LiveData<AuthToken> accesToken = loginModel;

    public void setLoginModel(LoginModel loginModel1){

        RetrofitClient.getInstance().verifyLoginUser(loginModel1).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                Log.d("Check", response.body().getAccessToken());
                if (response.isSuccessful() && response != null) {
                    loginModel.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {

            }
        });
    }
}
