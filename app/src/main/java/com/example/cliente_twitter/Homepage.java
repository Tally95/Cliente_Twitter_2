package com.example.cliente_twitter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class Homepage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        String username = getIntent().getStringExtra("username");

        TextView uname = findViewById(R.id.TV_username);
        uname.setText(username);
    }
}
