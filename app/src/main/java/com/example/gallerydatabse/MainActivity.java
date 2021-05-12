package com.example.gallerydatabse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    Button btnChoose,btnAdd,btnList;
    boolean st=false;
    ImageView img;

    final int REQUEST_CODE_Gallery=999;
    public static DatabaseHelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        img.setImageResource(R.mipmap.ic_launcher);
        databasehelper=new DatabaseHelper(this,"FoodDB.db",null,1);
        // databasehelper.onCreate(databasehelper);
        // databasehelper.queryData("CREATE TjABLE fgfdIF NOT EXISTs food(Id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR, price VARCHAR,image BLOB)");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st=true;
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_Gallery

                );
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databasehelper=new DatabaseHelper(this,"FoodDB.sqlite",null,1);
                // Toast.makeText(MainActivity.this,"Sucssefull",Toast.LENGTH_SHORT).show();
                if(st==true){

                    boolean b=databasehelper.insertData(et1.getText().toString().trim(),et2.getText().toString().trim(),imageTOByte(img));
                    Toast.makeText(MainActivity.this, "Success="+b, Toast.LENGTH_LONG).show();
                    et1.setText("");
                    et2.setText("");
                    img.setImageResource(R.mipmap.ic_launcher);
                    st=false;
                }
            else
                {
                    Toast.makeText(MainActivity.this, "First Choose Image and Set Input Box", Toast.LENGTH_LONG).show();
                }
           databasehelper.close();
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  databasehelper=new DatabaseHelper(MainActivity.this,"FoodDB.db",null,1);


                Intent intent = new Intent(MainActivity.this, FoodList.class);
                startActivity(intent);
                //  ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,show);
                // MaterialAlertDialogBuilder e = new MaterialAlertDialogBuilder(MainActivity.this);
                //e.setTitle("......Your Data......");
                // for(int i=0;i<show.size();i++)
//                e.setMessage(show.toString());
//                e.setCancelable(true);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_Gallery){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_Gallery);
            }
            else {
                Toast.makeText(this,"You don't have permision to access file location",Toast.LENGTH_SHORT);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE_Gallery==requestCode && resultCode==RESULT_OK && data!=null)
        {
            Uri uri=data.getData();
            try {
                InputStream inputstream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputstream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private byte[] imageTOByte(ImageView image)
    {
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    private  void init()
    {
        et1=findViewById(R.id.textfirst);
        et2=findViewById(R.id.textsecond);
        btnChoose=findViewById(R.id.btnchoose);
        btnAdd=findViewById(R.id.btnadd);
        btnList=findViewById(R.id.btnList);
        img=findViewById(R.id.img);

    }
}






















//package com.example.gallerydatabse;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.android.material.dialog.MaterialAlertDialogBuilder;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    EditText et1,et2;
//    Button btnChoose,btnAdd,btnList;
//
//    ImageView img;
//
//    final int REQUEST_CODE_Gallery=999;
//    public static DatabaseHelper databasehelper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        init();
//        databasehelper=new DatabaseHelper(this,"FoodDB.db",null,1);
//        // databasehelper.onCreate(databasehelper);
//        // databasehelper.queryData("CREATE TABLE IF NOT EXISTs food(Id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR, price VARCHAR,image BLOB)");
//        btnChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityCompat.requestPermissions(
//                        MainActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_CODE_Gallery
//
//                );
//            }
//        });
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //databasehelper=new DatabaseHelper(this,"FoodDB.sqlite",null,1);
//                // Toast.makeText(MainActivity.this,"Sucssefull",Toast.LENGTH_SHORT).show();
//
//                boolean b=databasehelper.insertData(et1.getText().toString().trim(),et2.getText().toString().trim(),imageTOByte(img));
//                Toast.makeText(MainActivity.this, "Success="+b, Toast.LENGTH_LONG).show();
//                et1.setText("");
//                et2.setText("");
//                img.setImageResource(R.mipmap.ic_launcher);
//
//            }
//        });
//        btnList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                databasehelper=new DatabaseHelper(MainActivity.this,"FoodDB.db",null,1);
//                ArrayList<String> show=databasehelper.fetch();
//                String toastText=show.toString();
//                Toast.makeText(getApplicationContext(),toastText,Toast.LENGTH_SHORT).show();
//                //  ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,show);
//                // MaterialAlertDialogBuilder e = new MaterialAlertDialogBuilder(MainActivity.this);
//                //e.setTitle("......Your Data......");
//                // for(int i=0;i<show.size();i++)
////                e.setMessage(show.toString());
////                e.setCancelable(true);
//            }
//        });
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==REQUEST_CODE_Gallery){
//            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
//
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,REQUEST_CODE_Gallery);
//            }
//            else {
//                Toast.makeText(this,"You don't have permision to access file location",Toast.LENGTH_SHORT);
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(REQUEST_CODE_Gallery==requestCode && resultCode==RESULT_OK && data!=null)
//        {
//            Uri uri=data.getData();
//            try {
//                InputStream inputstream=getContentResolver().openInputStream(uri);
//                Bitmap bitmap= BitmapFactory.decodeStream(inputstream);
//                img.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private byte[] imageTOByte(ImageView image)
//    {
//        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
//        byte[] byteArray=stream.toByteArray();
//        return byteArray;
//    }
//
//    private  void init()
//    {
//        et1=findViewById(R.id.textfirst);
//        et2=findViewById(R.id.textsecond);
//        btnChoose=findViewById(R.id.btnchoose);
//        btnAdd=findViewById(R.id.btnadd);
//        btnList=findViewById(R.id.btnList);
//        img=findViewById(R.id.img);
//
//    }
//}
//
//
//
