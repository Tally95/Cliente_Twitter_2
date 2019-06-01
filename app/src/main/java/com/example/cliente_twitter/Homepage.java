package com.example.cliente_twitter;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    private TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        ViewPager viewPager =(ViewPager)findViewById(R.id.viewpager);
        loadViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        String username = getIntent().getStringExtra("username");
        TextView uname = findViewById(R.id.TV_username);
        uname.setText(username);
    }

    private void loadViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(newInstance("Timeline"));
        adapter.addFragment(newInstance("New Tweet"));
        viewPager.setAdapter(adapter);
    }

    private TimelineFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
