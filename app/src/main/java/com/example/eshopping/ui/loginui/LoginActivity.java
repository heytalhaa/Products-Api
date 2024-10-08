package com.example.eshopping.ui.loginui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Observable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.eshopping.R;
import com.example.eshopping.ui.mainactivity.MainActivity;
import com.example.eshopping.data.retrofitclient.RetrofitClient;
import com.example.eshopping.data.model.AuthToken;
import com.example.eshopping.data.model.LoginModel;
import com.example.eshopping.databinding.ActivityLoginBinding;
import com.example.eshopping.ui.mainactivity.MainViewModel;
import com.example.eshopping.ui.signupui.SignupActivity;
import com.example.eshopping.utils.SharedPrefs;
import com.example.eshopping.utils.Utils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private boolean isPasswordVisible;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailET.getText().toString();
                String password = binding.passwordET.getText().toString();
                LoginModel data = new LoginModel();
                data.setEmail(email);
                data.setPassword(password);
                loginViewModel.setLoginModel(data);
                loginUser();
            }
        });


        binding.signUpTV.setOnClickListener(view -> {
            startActivity(new Intent(this, SignupActivity.class));
        });

        // Set an OnClickListener for the eye icon
        binding.eyelashIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // If password is visible, hide it
                    binding.passwordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.eyelashIV.setImageResource(R.drawable.visibility_off_24px);
                    isPasswordVisible = false;
                } else {
                    // If password is hidden, show it
                    binding.passwordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.eyelashIV.setImageResource(R.drawable.visibility_24px);
                    isPasswordVisible = true;
                }
                // Move the cursor to the end of the text
                binding.passwordET.setSelection(binding.passwordET.getText().length());
            }
        });
    }
    private void loginUser(){
        String email = binding.emailET.getText().toString();
        String password = binding.passwordET.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailET.setError("Email is Not Valid");
        } else if (password.isEmpty() || password.length()<4) {
            binding.passwordET.setError("Password Not Valid");
        }else {
            loginViewModel.accesToken.observe(this, new Observer<AuthToken>() {
                @Override
                public void onChanged(AuthToken authToken) {
                    Log.d("Loggedin", authToken.getAccessToken());
                    SharedPrefs.saveString(LoginActivity.this, "access_token", authToken.getAccessToken());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
    }
}