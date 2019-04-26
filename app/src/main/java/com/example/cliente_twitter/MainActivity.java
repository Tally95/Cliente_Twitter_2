package com.example.cliente_twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.identity.TwitterLoginButton ;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

public class MainActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "rvkgykCP5RWe4lrUkqVnOdDY9";
    private static final String TWITTER_SECRET =
            "gIJJtOt51fD3HspaNvNXH3RESV8uY1OvqFK4dcdjQf4jEhO6n1";

    private static final String TAG = "TwitterAuth";

    private FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener authListener;

    private TwitterLoginButton login_button;
    //private TextView userText;
    //private TextView statusText;
    //private ImageView imageView;
    //private esto es un comentariooo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(TWITTER_KEY,
                        TWITTER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);

        setContentView(R.layout.activity_main);

        login_button = (TwitterLoginButton) findViewById(R.id.login_button);

        login_button.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "login_button Callback: Success");
                Toast.makeText(MainActivity.this, "Authenticated!",
                        Toast.LENGTH_SHORT).show();
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                login(session);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "login_button Callback: Failure " +
                        exception.getLocalizedMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login_button.onActivityResult(requestCode, resultCode, data);
    }

    public void login(TwitterSession session){
        String username = session.getUserName();
        Intent intent = new Intent(MainActivity.this, Homepage.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


}
