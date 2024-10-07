package com.example.eshopping.ui.mainactivity.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eshopping.R;
import com.example.eshopping.data.model.ProductData;
import com.example.eshopping.databinding.FragmentHomeBinding;
import com.example.eshopping.ui.detailsactivity.DetailsActivity;
import com.example.eshopping.ui.mainactivity.MainActivity;
import com.example.eshopping.ui.mainactivity.MainViewModel;
import com.example.eshopping.ui.mainactivity.ProductAdapter;
import com.example.eshopping.utils.ApiResponse;
import com.example.eshopping.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private ProductAdapter productAdapter;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return (binding.getRoot());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getParentFragmentManager();

        //Associate with Fragment
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.apiData(); // Fetch Api method

        // Observe LiveData
       mainViewModel.getData.observe(getViewLifecycleOwner(), new Observer<ApiResponse<List<ProductData>>>() {
           @Override
           public void onChanged(ApiResponse<List<ProductData>> listApiResponse) {
               switch (listApiResponse.getStatus()){
                   case ONLOADING:
                       binding.progressBar.setVisibility(View.VISIBLE);
                       break;
                   case ONSUCCESS:
                       binding.progressBar.setVisibility(View.GONE);
                       productAdapter = new ProductAdapter(getContext(), listApiResponse.getData());
                       binding.productRV.setAdapter(productAdapter);
                       productAdapter.OnProductListener(new ProductAdapter.OnProductListener() {
                           @Override
                           public void onProductClicked(int position) {
                               // Passing Bundle of DATA Home Fragment to Details Fragment
                               Bundle bundle = new Bundle();
                               bundle.putInt("id", position);
                               DetailsFragment detailsFragment = new DetailsFragment();
                               detailsFragment.setArguments(bundle);
                               Utils.loadFragments(fragmentManager, R.id.fragmentContainer, detailsFragment, "details");
                           }
                       });
                       break;
                   case ONERROR:
                       binding.progressBar.setVisibility(View.GONE);
                       Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                       break;
               }
           }
       });
    }
}