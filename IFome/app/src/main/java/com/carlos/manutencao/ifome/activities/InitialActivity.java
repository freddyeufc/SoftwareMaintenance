package com.carlos.manutencao.ifome.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carlos.manutencao.ifome.R;
import com.carlos.manutencao.ifome.adapters.AdapterViewPagerInitial;
import com.carlos.manutencao.ifome.fragments.InitialFragment;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class InitialActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //View decorView = getWindow().getDecorView();
        //decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        initViewPager();

    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPagerInitialId);
        indicator = findViewById(R.id.indicatorInitialId);

        List<Fragment> listFragments = new ArrayList<>();

        listFragments.add(InitialFragment.newInstance(R.drawable.ic_android_black_24dp, "DigDin", "Primeiramente Digdin, parssa!"));
        listFragments.add(InitialFragment.newInstance(R.drawable.ic_android_black_24dp, "Manutenção", "Besti Cadeira ever!"));

        AdapterViewPagerInitial adapter = new AdapterViewPagerInitial(getSupportFragmentManager(), listFragments);

        viewPager.setOffscreenPageLimit(listFragments.size());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }
}
