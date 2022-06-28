package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class employePanelActivity extends AppCompatActivity {
    TextView btn1, btn2, btn3, btn4;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView logout;
    SharedPreferences sharedPreferences;
    private static final String shared_prefrence_name = "my_pref";
    private static final String key_name = "name";
    private static final String key_password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_panel);
        getSupportActionBar().hide();
        btn1 = findViewById(R.id.postBtn);
        btn2=findViewById(R.id.sellitemsBtn);
        logout=findViewById(R.id.logoutb);
        btn4=findViewById(R.id.pricing);
        btn3=findViewById(R.id.newsfeed);
        //by using theses lines user every time gets if they login from which google account
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        sharedPreferences = getSharedPreferences(shared_prefrence_name, MODE_PRIVATE);
        //to get the name and password of user that login
        String name = sharedPreferences.getString(key_name, null);
        String password = sharedPreferences.getString(key_password, null);
        //for logoout from application
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Toast.makeText(employePanelActivity.this, "logout succesfully", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(getApplicationContext(), loginPanelsActivity.class);
                startActivity(j);
                finish();
                //sign out and by using this everytime ask for new account
                gsc.signOut();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),userNewsFeedActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),BiddingActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),uploadsImageActivity.class);
                startActivity(intent);
            }
        });
        // for pricing of items
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PricingActivity.class);
                startActivity(intent);
            }
        });
    }


    //then add checkgglogin to if requestCode
// Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),SplashActivity.class);
        startActivity(intent);
    }
}