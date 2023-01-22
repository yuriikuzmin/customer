package com.kuzmin.customer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PayOrder extends AppCompatActivity {

    ClipData clipData;
    ClipboardManager clipboardManager;
    TextView tv_carta_master;
    Button btn_pay, btn_copy;
    Uri uri;
    String surl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        tv_carta_master=(TextView) findViewById(R.id.tv_carta_master);
        btn_copy=(Button) findViewById(R.id.btn_copy);
        surl="http://sbp.nspk.ru/participants/";
        uri=Uri.parse(surl);


    }

    public void onClickCopyCart(View view){
        clipData=ClipData.newPlainText("text", tv_carta_master.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
    }

    public void onClickPayBank(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);//переход на сайт для выбора банка
    }
}
