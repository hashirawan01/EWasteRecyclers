package com.example.Adapters;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.BiddingModel;
import com.example.ewasterecyclers.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class BiddingAdapter extends FirebaseRecyclerAdapter<BiddingModel, BiddingAdapter.myviewholder> {
    public BiddingAdapter(@NonNull FirebaseRecyclerOptions<BiddingModel> options) {
        super(options);
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biddingview, parent, false);
        return new myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final BiddingModel model) {
        holder.name.setText(model.getName());
        holder.phonenumber.setText(model.getPhoonnumber());
        holder.address.setText(model.getAddress());
        holder.bidprice.setText(model.getBiddingprice());
        holder.Biddername.setText(model.getBiddername());
        //we can get image from firebase from this
        String img = model.getImgUri();
        Picasso.get().load(img).into(holder.imageViewfeed);
    }


    class myviewholder extends RecyclerView.ViewHolder {
        TextView name, phonenumber, address,bidprice,Biddername;
        ImageView imageViewfeed;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nametextb);
            bidprice = (TextView) itemView.findViewById(R.id.bidingPricesb);
            phonenumber = (TextView) itemView.findViewById(R.id.phoonnumberb);
            imageViewfeed = itemView.findViewById(R.id.imageViewfeedb);
            Biddername=itemView.findViewById(R.id.biddername);
            address = (TextView) itemView.findViewById(R.id.addressb);
        }
    }
}
