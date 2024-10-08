package com.example.eshopping.ui.mainactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.eshopping.data.model.CreateUser;
import com.example.eshopping.R;
import com.example.eshopping.databinding.ActivityMainBinding;
import com.example.eshopping.ui.mainactivity.adapter.ProductAdapter;
import com.example.eshopping.ui.mainactivity.auth.AccountFragment;
import com.example.eshopping.ui.mainactivity.auth.CartFragment;
import com.example.eshopping.ui.mainactivity.auth.HomeFragment;
import com.example.eshopping.ui.mainactivity.auth.MessagesFragment;
import com.example.eshopping.utils.SharedPrefs;
import com.example.eshopping.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ProductAdapter productAdapter;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBar.toolBar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Utils.loadFragments(fragmentManager, R.id.fragmentContainer, new HomeFragment(), "home");

        CreateUser user = SharedPrefs.getObject(this, "userData", CreateUser.class);
        binding.appBar.toolBar.setTitle(user.getName());


//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mainViewModel.apiData();
//        mainViewModel.getData().observe(this, new Observer<List<ProductData>>() {
//            @Override
//            public void onChanged(List<ProductData> productData) {
//                binding.progressBar.setVisibility(View.INVISIBLE);
//                productAdapter = new ProductAdapter(MainActivity.this, productData);
//                binding.productRV.setAdapter(productAdapter);
//                productAdapter.OnProductListener(new ProductAdapter.OnProductListener() {
//                        @Override
//                        public void onProductClicked(int position) {
//                            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                            intent.putExtra("id", position);
//                            startActivity(intent);
//                        }
//                    });
//            }
//        });

        binding.navigationV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selectedId = item.getItemId();

                if (selectedId == R.id.homeBN){
                    Utils.loadFragments(fragmentManager, R.id.fragmentContainer, new HomeFragment(), "home");
                    return true;
                } else if (selectedId == R.id.msgBN){
                    Utils.loadFragments(fragmentManager, R.id.fragmentContainer, new MessagesFragment(), "messages");
                    return true;
                } else if (selectedId == R.id.cartBN) {
                    Utils.loadFragments(fragmentManager, R.id.fragmentContainer, new CartFragment(), "cart");
                    return true;
                } else if (selectedId == R.id.accountBN) {
                    Utils.loadFragments(fragmentManager, R.id.fragmentContainer, new AccountFragment(), "account");
                    return true;
                } else {
                    return false;
                }
            }
        });

//        init();

//        RetrofitClient.getInstance().getProduct().enqueue(new Callback<List<ProductData>>() {
//            @Override
//            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
////                Log.d("response", response.body().get(1).getImages().toString());
//                if (!response.body().isEmpty()) {
//                    binding.progressBar.setVisibility(View.INVISIBLE);
//                     productAdapter = new ProductAdapter(MainActivity.this, response.body());
//                    binding.productRV.setAdapter(productAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductData>> call, Throwable t) {
//                Log.d("response", t.getLocalizedMessage());
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        
        if (current instanceof HomeFragment){
            finish();
        } else {
            super.onBackPressed();
        }

        // Synchronize the bottom navigation view
        int currentFragment = fragmentManager.getBackStackEntryCount();
        switch (currentFragment) {
            case 1:
                binding.navigationV.setSelectedItemId(R.id.homeBN);
                break;
            case 2:
                binding.navigationV.setSelectedItemId(R.id.msgBN);
                break;
            case 3:
                binding.navigationV.setSelectedItemId(R.id.cartBN);
                break;
            case 4:
                binding.navigationV.setSelectedItemId(R.id.accountBN);
                break;
        }
    }
    //    private void init(){
//        getAllProducts();
//    }

//    private void getAllProducts() {
//        RetrofitClient.getInstance().getProduct().enqueue(new Callback<List<ProductData>>() {
//            @Override
//            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
//                if (!response.body().isEmpty()) {
//                    binding.progressBar.setVisibility(View.INVISIBLE);
//                    productAdapter = new ProductAdapter(MainActivity.this, response.body());
//                    binding.productRV.setAdapter(productAdapter);
//
//                    productAdapter.OnProductListener(new ProductAdapter.OnProductListener() {
//                        @Override
//                        public void onProductClicked(int position) {
//                            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                            intent.putExtra("id", position);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductData>> call, Throwable t) {
//
//            }
//        });
//    }
}