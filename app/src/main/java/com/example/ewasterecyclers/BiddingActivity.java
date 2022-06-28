package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.Adapters.BiddingAdapter;
import com.example.Adapters.newsFeedAdapter;
import com.example.Models.BiddingModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BiddingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BiddingAdapter biddingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);
        recyclerView = findViewById(R.id.recyclerView);
        ProgressDialog dialog = new ProgressDialog(BiddingActivity.this);
        dialog.setMessage("Data is loading");
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<BiddingModel> options =
                new FirebaseRecyclerOptions.Builder<BiddingModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BiddingPrices"), BiddingModel.class)
                        .build();

        biddingAdapter = new BiddingAdapter(options);
        recyclerView.setAdapter(biddingAdapter);
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
        biddingAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        biddingAdapter.stopListening();

    }
}