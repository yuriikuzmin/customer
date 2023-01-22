package com.kuzmin.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    TextView text_order;
    EditText et_problem, et_address, et_name, et_phone, et_date;
    Button btn_save, btn_order, btn_pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //активность развернули на весь экран

        text_order = (TextView) findViewById(R.id.text_order_add);
        et_problem = (EditText) findViewById(R.id.et_problem);
        et_address = (EditText) findViewById(R.id.et_address);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_date = (EditText) findViewById(R.id.et_deadline);
        btn_save=(Button) findViewById(R.id.btn_save_order);
        btn_order=(Button) findViewById(R.id.btn_order);
        btn_pay=(Button) findViewById(R.id.btn_pay_order);
        btn_order.setVisibility(View.GONE);
        btn_pay.setVisibility(View.GONE);

    }

    public void onClickSaveOrder(View view) {
        String problem = et_problem.getText().toString();
        String address = et_address.getText().toString();
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String date = et_date.getText().toString();
        new OrderData(MainActivity.this, text_order).execute(problem, address, name, phone, date);
        Log.d("LOG", problem+", "+address+", "+name+", "+phone+", "+date);
    }

    public  void onClickOrder(View view){
        Intent intent=new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        btn_order.setVisibility(View.GONE);
        btn_pay.setVisibility(View.GONE);
    }

    public class OrderData extends AsyncTask<String, Void, String> {
        Context context;
        TextView message_order;

        public OrderData() {
        }

        public OrderData(Context context, TextView text_order) {
            this.context = context;
            this.message_order = text_order;
        }

        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg) {
            try {
                String problem = arg[0];
                String address = arg[1];
                String name = arg[2];
                String phone = arg[3];
                String deadline = arg[4];

                String link = "http://192.168.1.2/customerOrder.php";
                String data = URLEncoder.encode("problem", "UTF-8") + "=" + URLEncoder.encode(problem, "UTF-8") + " && " + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + " && " + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + " && " + URLEncoder.encode("deadline", "UTF-8") + "=" + URLEncoder.encode(deadline, "UTF-8");
                URL url = new URL(link);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream ops = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();

                InputStream ips = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                reader.close();
                ips.close();
                conn.disconnect();
                return sb.toString();

            } catch (Exception e) {
                return new String("Exeption: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String data) {
            et_address.setVisibility(View.GONE);
            et_date.setVisibility(View.GONE);
            et_name.setVisibility(View.GONE);
            et_phone.setVisibility(View.GONE);
            et_problem.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            btn_order.setVisibility(View.VISIBLE);
            btn_pay.setVisibility(View.VISIBLE);
            this.message_order.setText(data);
            Log.d("LOG", "Проверили на содержание ответ" + data.toString());
        }
    }
}