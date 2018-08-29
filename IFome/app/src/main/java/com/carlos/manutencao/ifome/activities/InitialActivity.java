package com.carlos.manutencao.ifome.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carlos.manutencao.ifome.R;
import com.carlos.manutencao.ifome.adapters.AdapterViewPagerInitial;
import com.carlos.manutencao.ifome.fragments.InitialFragment;

import java.util.ArrayList;
import java.util.List;

public class InitialActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        initViewPager();


    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPagerInitialId);

        List<Fragment> listFragments = new ArrayList<>();

        listFragments.add(InitialFragment.newInstance(R.drawable.ic_android_black_24dp, "Teste 01"));
        listFragments.add(InitialFragment.newInstance(R.drawable.ic_android_black_24dp, "Teste 02"));

        AdapterViewPagerInitial adapter = new AdapterViewPagerInitial(getSupportFragmentManager(), listFragments);

        viewPager.setOffscreenPageLimit(listFragments.size());
        viewPager.setAdapter(adapter);
    }
}
