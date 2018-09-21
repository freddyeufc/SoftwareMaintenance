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

public class SplashActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator indicator;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginButton = findViewById(R.id.loginButtonId);
        registerButton = findViewById(R.id.registerButtonId);
        initViewPager();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(login);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
            }
        });

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
