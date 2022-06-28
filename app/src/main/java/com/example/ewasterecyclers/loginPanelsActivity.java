package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ewasterecyclers.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class loginPanelsActivity extends AppCompatActivity {
TextView t1,t2;
    SharedPreferences sharedPreferences;
    private static final String shared_prefrence_name = "my_pref";
    private static final String key_name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_panels);
        t1=findViewById(R.id.employeeAccount);
        t2=findViewById(R.id.simpleuserAccount);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),userLoginAcctivity.class);
                startActivity(intent);

            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(shared_prefrence_name, MODE_PRIVATE);
        String name = sharedPreferences.getString(key_name, null);
        if (name != null) {
            Intent intent = new Intent(loginPanelsActivity.this, userHomePanelActivity.class);
            startActivity(intent);
        }
        // Check if user is signed in (non-null) and update UI accordingly.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(loginPanelsActivity.this);
        if (account != null) {
            startActivity(new Intent(getApplicationContext(), userHomePanelActivity.class));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),SplashActivity.class);
        startActivity(intent);
    }

}