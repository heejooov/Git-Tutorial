package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class dummy_serviDetail extends AppCompatActivity {

    ImageView svDetail_menu;
    TextView servi_detail1, servi_detail2,  sv_Name;
    String service_ID;
    Button url_button;
    String url="#";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_servi_detail);
        getSupportActionBar().hide();

        service_ID = getIntent().getStringExtra("service_key");

        svDetail_menu= (ImageView)findViewById(R.id.svDetail_menu);
        svDetail_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sv_Name= (TextView)findViewById(R.id.sv_Name);
        servi_detail1= (TextView)findViewById(R.id.servi_detail1);
        servi_detail2= (TextView)findViewById(R.id.servi_detail2);
        url_button = (Button)findViewById(R.id.url_button);

        servi_detail1.setText("test1 : "+service_ID);
        servi_detail2.setText("test2");

        set_DetailText(service_ID);
        url_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bokjiro.go.kr/nwel/bokjiroMain.do"));
                startActivity(intent);
            }
        });

    }
    //리스트 더하기
    public void set_DetailText(String key){
        changeCode apiapi=new changeCode();
        String[] str=apiapi.callApi2(service_ID);
        sv_Name.setText(str[0]);
        servi_detail1.setText(str[1]);
        servi_detail2.setText(str[2]);
        url=str[3];

    }
}
