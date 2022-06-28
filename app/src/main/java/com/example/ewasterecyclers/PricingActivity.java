package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.Adapters.RateListAdapters;
import com.example.Models.Item;
import com.example.Models.RateListModel;

import java.util.ArrayList;
import java.util.List;

public class PricingActivity extends AppCompatActivity {
    GridView simpleList;
    ArrayList birdList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        simpleList = (GridView) findViewById(R.id.simpleGridView);
        birdList.add(new Item("Mobile","1k to 5k",R.drawable.telephonr));
        birdList.add(new Item("Computer","100 to 1500",R.drawable.comp));
        birdList.add(new Item("Screen","1k to 5k",R.drawable.screens));
        birdList.add(new Item("Laptop","1k to 6k",R.drawable.laptop));
        birdList.add(new Item("Vacume","1k to 2k",R.drawable.vacum));
        birdList.add(new Item("Calculator","100 to 1k",R.drawable.calculator));
        birdList.add(new Item("Washing M","1k to 3k",R.drawable.washiung));
        birdList.add(new Item("Oven","100 to 1k",R.drawable.oven));
        birdList.add(new Item("Ac","1k to 1k",R.drawable.ac));
        birdList.add(new Item("Clock","100 to 1k",R.drawable.clock));
        MyAdapter myAdapter=new MyAdapter(this,R.layout.pricelist,birdList);
        simpleList.setAdapter(myAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    Toast.makeText(PricingActivity.this, "first itemn clicked", Toast.LENGTH_SHORT).show();
                }
                else if (position==1)
                {
                    Toast.makeText(PricingActivity.this, "second itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==2)
                {
                    Toast.makeText(PricingActivity.this, "third itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==3)
                {
                    Toast.makeText(PricingActivity.this, "fourth itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==4)
                {
                    Toast.makeText(PricingActivity.this, "fifth itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==5)
                {
                    Toast.makeText(PricingActivity.this, "Sixth itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==6)
                {
                    Toast.makeText(PricingActivity.this, "seventh itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==7)
                {
                    Toast.makeText(PricingActivity.this, "Eight itemn clicked", Toast.LENGTH_SHORT).show();

                }
                else if (position==8)
                {
                    Toast.makeText(PricingActivity.this, "ninth itemn clicked", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}