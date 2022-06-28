package com.example.ewasterecyclers;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class signUpActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleApiClient googleApiClient;
    TextView textView2;
    EditText TextPersonName,TextEmailAddress,password1;
    Button register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

// ...
// Initialize Firebase Auth

    ImageView fbbtn,ggogle_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        textView2=findViewById(R.id.textView2);
        TextPersonName=findViewById(R.id.TextPersonName);
        TextEmailAddress=findViewById(R.id.TextEmailAddress);
        password1=findViewById(R.id.password1);
        register=findViewById(R.id.register);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        fbbtn = findViewById(R.id.fb);
        ggogle_btn=findViewById(R.id.google);
        facebookLoginAccess();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performauth();
            }
        });




        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginManager.getInstance().logInWithReadPermissions(signUpActivity.this, Arrays.asList("email"));
            }

        });
        googleLoginAccess();
        ggogle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                googleSignIn();
            }
        });

    }
    private void performauth() {
        String name=TextPersonName.getText().toString();
        String email=TextEmailAddress.getText().toString();
        String password=password1.getText().toString();

        if(!email.matches(emailPattern))
        {
            TextEmailAddress.setError("Enter the correct email address");
        }
        else if(password.isEmpty() || password.length()<6)
        {
            password1.setError("enter valid password");

        }
        else if(name.isEmpty())
        {
            TextPersonName.setError("please enter your Name ");
        }
        else{
            progressDialog.setMessage("please wait for Registration....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sentUsertoNewactivity();
                        Toast.makeText(signUpActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(signUpActivity.this, "Error occured while registrating", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }

    private void googleSignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 100);
    }

    private void googleLoginAccess() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);


    }


    private void facebookLoginAccess() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(getApplicationContext(), userHomePanelActivity.class);
                        startActivity(intent);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        finish();

                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "on Cancel");

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "on Error" + exception);
                        Toast.makeText(signUpActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(signUpActivity.this, "sign up succssfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(signUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
                handleGoogleLoginResult(data);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleGoogleLoginResult(Intent data) {
        if (data != null) {
            // get result from data received
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statusCode = result.getStatus().getStatusCode();
            if (result.isSuccess() && result.getSignInAccount() != null) {
                // Signed in successfully

            }
            // logout
            if (googleApiClient != null && googleApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(googleApiClient);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(signUpActivity.this, "sign up with credential successfull", Toast.LENGTH_SHORT).show();
                          //  FirebaseUser user = mAuth.getCurrentUser();
                            HomeActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(signUpActivity.this, "signinfailed", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(signUpActivity.this, "sign up with credential Failed", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }

    private void HomeActivity() {
        Intent intent=new Intent(getApplicationContext(),userHomePanelActivity.class);
        startActivity(intent);
        finish();
    }
    private void  sentUsertoNewactivity() {
        Intent intent=new Intent(signUpActivity.this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(signUpActivity.this);
        if (account != null) {
            startActivity(new Intent(getApplicationContext(), userHomePanelActivity.class));
        }

            
    }


}