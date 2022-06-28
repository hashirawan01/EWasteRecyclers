package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.Adapters.UserNewsFeed;
import com.example.Adapters.newsFeedAdapter;
import com.example.Models.CustomerDataModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class userNewsFeedActivity extends AppCompatActivity {
    RecyclerView reciew;
    UserNewsFeed myadapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_news_panel);
        reciew = findViewById(R.id.recyclerView);
        ProgressDialog dialog = new ProgressDialog(userNewsFeedActivity.this);
        dialog.setMessage("Data is loading");
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        reciew.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<CustomerDataModel> options =
                new FirebaseRecyclerOptions.Builder<CustomerDataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customer"), CustomerDataModel.class)
                        .build();

        myadapter1 = new UserNewsFeed(options);
        reciew.setAdapter(myadapter1);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 4000);



    }


    @Override
    protected void onStart() {
        super.onStart();
        myadapter1.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        myadapter1.stopListening();
    }

}