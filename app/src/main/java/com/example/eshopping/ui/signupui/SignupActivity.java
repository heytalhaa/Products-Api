package com.example.eshopping.ui.signupui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.eshopping.R;
import com.example.eshopping.data.retrofitclient.RetrofitClient;
import com.example.eshopping.data.model.CreateUser;
import com.example.eshopping.databinding.ActivitySignupBinding;
import com.example.eshopping.ui.loginui.LoginActivity;
import com.example.eshopping.ui.mainactivity.MainActivity;
import com.example.eshopping.utils.SharedPrefs;
import com.example.eshopping.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private boolean isPasswordVisible;
    private SignupViewModel signupViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        signupViewModel._createUser.observe(this, new Observer<CreateUser>() {
            @Override
            public void onChanged(CreateUser createUser) {

                SharedPrefs.saveObjects(SignupActivity.this, "userData", createUser);
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        });
        binding.signupBtn.setOnClickListener(view -> {
            String email = binding.emailET.getText().toString();
            String name = binding.nameET.getText().toString();
            String password = binding.passwordET.getText().toString();
            String image = "https://api.lorem.space/image/face?w=640&h=480";
            CreateUser createUser = new CreateUser();
            createUser.setName(name);
            createUser.setEmail(email);
            createUser.setPassword(password);
            createUser.setAvatar(image);
            signupViewModel.set_createUser(createUser);

//            RetrofitClient.getInstance().createNewUser(createUser).enqueue(new Callback<CreateUser>() {
//                @Override
//                public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
//                    if (response.isSuccessful() && response != null){
//                        Toast.makeText(SignupActivity.this, "Welcome New User", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<CreateUser> call, Throwable t) {
//
//                }
//            });
        });

        binding.loginTV.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
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
}