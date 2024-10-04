package com.example.eshopping.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

public class Utils {

    public static void loadFragments(FragmentManager fragmentManager, int fragmentContainer, Fragment fragment, String tag){

        FragmentTransaction  fragmentTransaction =fragmentManager.beginTransaction();
        Fragment currentFragment =fragmentManager.findFragmentByTag(tag);
        if (currentFragment==null) {
            fragmentTransaction
                    .replace(fragmentContainer, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        }else {
            fragmentManager.popBackStack(tag, 0);
        }
    }

}
