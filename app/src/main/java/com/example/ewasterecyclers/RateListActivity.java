package com.example.ewasterecyclers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;

import com.example.Adapters.RateListAdapters;
import com.example.Models.RateListModel;

import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends AppCompatActivity {
    GridView modelclasslist;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        recyclerView = findViewById(R.id.recyclerVieW);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<RateListModel> ModelclassList = new ArrayList<>();
        ModelclassList.add(new RateListModel(R.drawable.telephonr, "Mobiles", "hellow this is user item"));
        ModelclassList.add(new RateListModel(R.drawable.comp, "Computers", "hellow this is 2nd user item"));
        ModelclassList.add(new RateListModel(R.drawable.screens, "Screens", "hellow this is 3rd user item"));
        ModelclassList.add(new RateListModel(R.drawable.laptop, "Laptops", "hellow this is 4th user item"));
        ModelclassList.add(new RateListModel(R.drawable.vacum, "Vacume cleaner", "hellow this is 5th user item"));
        ModelclassList.add(new RateListModel(R.drawable.calculator, "Calculators", "hellow this is 6thuser item"));
        ModelclassList.add(new RateListModel(R.drawable.washiung, "Washing Machine", "hellow this is 7thuser item"));
        ModelclassList.add(new RateListModel(R.drawable.oven, "Oven", "hellow this is 8thuser item"));
        ModelclassList.add(new RateListModel(R.drawable.ac, "Ac", "hellow this is 9thuser item"));
        ModelclassList.add(new RateListModel(R.drawable.clock, "Clock", "hellow this is 10thuser item"));
        RateListAdapters Adapter = new RateListAdapters(ModelclassList, this);
        recyclerView.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();
//        MyAdapter myAdapter=new MyAdapter(this,R.layout.grid_view_items,birdList);
//        simpleList.setAdapter(myAdapter);


    }
}