package com.kuzmin.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text_order;
    EditText et_problem, et_address, et_name, et_phone, et_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //активность развернули на весь экран

        text_order =(TextView) findViewById(R.id.text_order_add);
        et_problem = (EditText) findViewById(R.id.et_problem);
        et_address = (EditText) findViewById(R.id.et_address);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_date = (EditText) findViewById(R.id.et_deadline);

    }
    public void onClickSaveOrder(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public class OrderData extends AsyncTask <String, Void, String> {

        @Override
        protected String doInBackground(String... arg) {

            return null;
        }
    }
}