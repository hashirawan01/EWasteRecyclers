package com.example.Adapters;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.BiddingModel;
import com.example.ewasterecyclers.R;
import com.example.Models.CustomerDataModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserNewsFeed extends FirebaseRecyclerAdapter<CustomerDataModel, UserNewsFeed.myviewholder> {
    public UserNewsFeed(@NonNull FirebaseRecyclerOptions<CustomerDataModel> options) {
        super(options);
    }

    //this is to get the name of that person that is login
    private static final String shared_prefrence_name = "my_pref";
    private static final String key_name = "name";
    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(shared_prefrence_name, MODE_PRIVATE);
    String Biddername = sharedPreferences.getString(key_name, null);

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usernewsfeed, parent, false);
        return new myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final CustomerDataModel model) {
        holder.name.setText(model.getName());
        holder.phonenumber.setText(model.getPhoonenumber());
        holder.address.setText(model.getAddress());


        String name = model.getName();
        String phoonenumber = model.getPhoonenumber();
        String address = model.getAddress();
        //we can get image from firebase from this
        String img = model.getImageuri();
        Picasso.get().load(img).into(holder.imageViewfeed);
        holder.imageViewfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true, 600)
                        .create();
                View myview = dialogPlus.getHolderView();
                ;
                final EditText bidding = myview.findViewById(R.id.biddinge2);


                Button submit = myview.findViewById(R.id.usubmit);
                dialogPlus.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        String bidingp = bidding.getText().toString();

                        //to set data of bidding prices to firebase database
                        BiddingModel biddingModel = new BiddingModel(img, name, phoonenumber, address, bidingp, Biddername);
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference node = db.getReference("BiddingPrices");
                        //to set the random id of every node
                        String randomid = UUID.randomUUID().toString();
                        node.child(randomid).setValue(biddingModel);
                        dialogPlus.dismiss();

                    }
                });


            }
        });



    }


    class myviewholder extends RecyclerView.ViewHolder {
        TextView name, phonenumber, address;
        ImageView edit, delete, imageViewfeed;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nametext);
            phonenumber = (TextView) itemView.findViewById(R.id.phoonnumber);
            imageViewfeed = itemView.findViewById(R.id.imageViewfeed);
            address = (TextView) itemView.findViewById(R.id.address);
            edit = (ImageView) itemView.findViewById(R.id.editicon);
        }
    }
}
