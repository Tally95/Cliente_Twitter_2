package com.example.cliente_twitter;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manual);
    }

    public void goHomepage(View view) {
        String username = "Manual Login Simulation, there is no connection with the Twitter API";
        Intent intent  = new Intent(LoginManualActivity.this, Homepage.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
