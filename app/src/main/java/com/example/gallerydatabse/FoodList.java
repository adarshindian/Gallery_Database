package com.example.gallerydatabse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {
    GridView gridView;
    ArrayList<Food>show;
    FoodListAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        gridView = (GridView) findViewById(R.id.gridView);
        show =MainActivity.databasehelper.fetch();
        adapter = new FoodListAdapter(this, R.layout.fooditem, show);
        gridView.setAdapter(adapter);
        // show=MainActivity.databasehelper.fetch();
        String toastText=show.get(1).getName().toString();
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),toastText,Toast.LENGTH_SHORT).show();
    }
}