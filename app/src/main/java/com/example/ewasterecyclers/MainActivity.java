package com.example.ewasterecyclers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mauth;
    FirebaseUser muser;


    TextView new_acc;
    EditText editText_name, editText_password;
    Button login;
    SharedPreferences sharedPreferences;
    private static final String shared_prefrence_name = "my_pref";
    private static final String key_name = "name";
    private static final String key_password = "password";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(this);
        mauth = FirebaseAuth.getInstance();
        muser = mauth.getCurrentUser();
        editText_name = findViewById(R.id.editText_name);
        login = findViewById(R.id.login1);
        editText_password = findViewById(R.id.editText_password);
        new_acc = findViewById(R.id.new_acc);
        sharedPreferences = getSharedPreferences(shared_prefrence_name, MODE_PRIVATE);
        String name = sharedPreferences.getString(key_name, null);
        if (name != null) {
            Intent intent = new Intent(MainActivity.this, userHomePanelActivity.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginmethod();


            }


        });
        new_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(j);
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                String[] For_split_email = key_name.split("[@._]");
//                for (int n = 0; n <= For_split_email.length - 1; n++)
//                {
//                    Toast.makeText(getApplicationContext(),  For_split_email[n], Toast.LENGTH_SHORT).show();
//                }

            }
        });

    }

    private void loginmethod() {
        String email = editText_name.getText().toString();
        String password = editText_password.getText().toString();

        if (!email.matches(emailPattern)) {
            editText_name.setError("Enter the correct email address");
        } else if (password.isEmpty() || password.length() < 6) {
            editText_password.setError("enter valid password");

        } else {
            progressDialog.setMessage("please wait login started....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key_name, editText_name.getText().toString());
            editor.putString(key_password, editText_password.getText().toString());
            editor.apply();
            mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sentUsertoNewactivity();
                        Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error occured while login", Toast.LENGTH_SHORT).show();
                          progressDialog.dismiss();
                    }
                }
            });
        }
    }

    private void sentUsertoNewactivity() {
        Intent intent = new Intent(MainActivity.this, userHomePanelActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

