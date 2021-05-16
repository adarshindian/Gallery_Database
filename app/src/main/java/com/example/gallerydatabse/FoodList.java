package com.example.gallerydatabse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class FoodList extends AppCompatActivity {
    GridView gridView;
    ArrayList<Food>show;
    FoodListAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        gridView =  findViewById(R.id.gridView);
        show =MainActivity.databasehelper.fetch();
        adapter = new FoodListAdapter(this, R.layout.fooditem, show);
        gridView.setAdapter(adapter);
        // show=MainActivity.databasehelper.fetch();
       // String toastText=show.get(1).getName().toString();
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener((parent, view, position, id) -> showDialogue(position));
       // Toast.makeText(getApplicationContext(),toastText,Toast.LENGTH_SHORT).show();
    }


    public void showDialogue(int position){
        Dialog dialogue=new Dialog(FoodList.this);
        dialogue.setContentView(R.layout.samplemyview);
        //Getting custom Dialogue Views
        TextView text_inside=dialogue.findViewById(R.id.text_of_Image);
        ImageView img_inside=dialogue.findViewById(R.id.image);
       // int img=show[position];

        Button cancel=dialogue.findViewById(R.id.Cancel);
        Cursor c = MainActivity.databasehelper.getData("SELECT id FROM FOOD");
        ArrayList<Integer> arrID = new ArrayList<Integer>();
        while (c.moveToNext()){
            arrID.add(c.getInt(0));
        }
//        String trim = text_inside.get(position).toString().trim();
//        text_inside.setText(String.valueOf(trim));
        // Integer c=imageids.get(position);
        // text_inside.setText(String.valueOf(imageids.get(position)).trim());

       // String title=getResources().getResourceName();
       // text_inside.setText(title.substring(title.indexOf("/")+1));
        img_inside.setImageResource(ByteBuffer.wrap(show.get(position).getImage()).getInt());
        dialogue.show();
        // Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue.dismiss();
            }
        });

    }
}