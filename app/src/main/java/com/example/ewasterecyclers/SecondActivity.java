package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    ImageView img;
    TextView textView;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        img = findViewById(R.id.imageView);
        textView = findViewById(R.id.name);
        btn = findViewById(R.id.logoutBtn);
       facebookcredentialSetter();
        googleCredentialSetter();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                //sign out and by using this everytime ask for new account
                gsc.signOut();
                Intent intent = new Intent(getApplicationContext(), signUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void googleCredentialSetter() {
         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null)
        {
            String Name=account.getDisplayName();
            String Email=account.getEmail();
            account.getId();
            account.getDisplayName();
            textView.setText(Email);
        }



    }

    private void facebookcredentialSetter() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        AccessToken.getCurrentAccessToken().getPermissions();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String fullName = object.getString("name");

                            String name=object.getString("email");
                            Log.wtf("fullname",name);
//
                            textView.setText(name);
                            String url = object.getJSONObject("picture").getJSONObject("data").getString("url");
                            Picasso.get().load(url).into(img);
                            Log.wtf("Tag", "FirebaseData");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Application code
                    }
                });
        Bundle parameters = new Bundle();
        //to get the email name and id of facebook login user
        parameters.putString("fields","id,email,name,link,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();

    }
}