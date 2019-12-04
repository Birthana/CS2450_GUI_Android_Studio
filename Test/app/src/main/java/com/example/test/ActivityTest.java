package com.example.test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTest extends AppCompatActivity implements FragmentA.FragmentAListener {
    //public static final String EXTRA_NUMBER = "com.example.test.EXTRA_NUMBER";
    private FragmentA fragmentA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fragmentA = new FragmentA();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .commit();
    }

    @Override
    public void onInputASent(CharSequence input) {
        Log.d("Test", "" + input.toString());
    }
}
