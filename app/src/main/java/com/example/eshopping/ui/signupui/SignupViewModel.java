package com.example.eshopping.ui.signupui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eshopping.data.model.CreateUser;
import com.example.eshopping.data.retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel extends ViewModel {
    private MutableLiveData<CreateUser> createUser = new MutableLiveData<>();

    public LiveData<CreateUser> _createUser = createUser;

    public void set_createUser(CreateUser createUser1){
        RetrofitClient.getInstance().createNewUser(createUser1).enqueue(new Callback<CreateUser>() {
            @Override
            public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
                if (response.isSuccessful() && response !=null){
                    createUser.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CreateUser> call, Throwable t) {

            }
        });
    }
}
