package com.example.eshopping.ui.onboardingscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.eshopping.R;
import com.example.eshopping.data.model.OnBoardingData;
import com.example.eshopping.databinding.ActivityOnBoardingBinding;
import com.example.eshopping.ui.loginui.LoginActivity;
import com.example.eshopping.utils.SharedPrefs;
import com.example.eshopping.utils.Utils;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
    private ActivityOnBoardingBinding binding;

    private OnBoardingAdapter onBoardingAdapter;
    private OnBoardingData onBoardingData;
    private ArrayList<OnBoardingData> onBoardingDataArrayList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        sharedPreferences = getSharedPreferences("onBoardingPrefs", MODE_PRIVATE);

        onBoardingDataArrayList = new ArrayList<>();

        onBoardingData = new OnBoardingData();
        onBoardingData.setImage(R.drawable.mainlogo);
        onBoardingData.setTitle("EVERYTHING YOU WANT");
        onBoardingData.setDetails("Select product by your own choice and make your shopping easier!");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingData = new OnBoardingData();
        onBoardingData.setImage(R.drawable.delivery_boy);
        onBoardingData.setTitle("FAST DELIVERY");
        onBoardingData.setDetails("Delivery on time!");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingData = new OnBoardingData();
        onBoardingData.setImage(R.drawable.cartlogo);
        onBoardingData.setTitle("EVERYTHING YOU WANT");
        onBoardingData.setDetails("Select product by your own choice and make your shopping easier!");
        onBoardingDataArrayList.add(onBoardingData);

         onBoardingAdapter = new OnBoardingAdapter(this, onBoardingDataArrayList);
        binding.viewPager.setAdapter(onBoardingAdapter);

        //change callBack on page selector
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changedIndicator(position);
            }
        });

        //Button listener Intent Onboarding to loginActivity
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.viewPager.getCurrentItem() +1 <onBoardingAdapter.getItemCount()){
                    binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                }else {

                        // Save in SharedPreferences
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("isOnBoardingCompleted", true);
//                        editor.apply();
                    SharedPrefs.saveBoolean(OnBoardingActivity.this, "OnBoarding",true);

                        // navigate to login activity
                        startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                         finish();
                }
            }
        });

    }

    // Change indicator method
    private void changedIndicator(int position) {
        switch (position){
            case 0:
                binding.c1.setCardBackgroundColor(getResources().getColor(R.color.strongCyan));
                binding.c2.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c3.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case 1:
                binding.c1.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c2.setCardBackgroundColor(getResources().getColor(R.color.strongCyan));
                binding.c3.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case 2:
                binding.c1.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c2.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c3.setCardBackgroundColor(getResources().getColor(R.color.strongCyan));
                break;
            default:
                Toast.makeText(this, "Invalid Case", Toast.LENGTH_SHORT).show();
        }
        if (position == 2){
            binding.nextBtn.setText("Start");
        }else {
            binding.nextBtn.setText("Next");
        }
    }
}