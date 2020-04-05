package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class inform extends AppCompatActivity {

    LinearLayout l_preg,l_baby,l_stud,l_adult,l_middle,l_old,l_disa,l_one,l_multi,l_low;
    ImageView i_preg,i_baby,i_stud,i_adult,i_middle,i_old,i_disa,i_one,i_multi,i_low;
    TextView tv_preg,tv_baby,tv_stud,tv_adult,tv_middle,tv_old,tv_disa,tv_one,tv_multi,tv_low;
    EditText et_name,et_age,et_area,et_gender;
    RadioGroup gender_group;
    RadioButton gender_w, gender_m;
    Button bt_join;
    String name, age, gender, area;
    boolean preg,baby,stud,adult,middle,old;
    boolean disa,one,multi,low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        bt_join=(Button)findViewById(R.id.btn_join_inform);

        et_name=(EditText)findViewById(R.id.et_name_inform);
        et_age=(EditText)findViewById(R.id.et_age_inform);

        l_preg=(LinearLayout)findViewById(R.id.l_pregnant_inform);
        l_baby=(LinearLayout)findViewById(R.id.l_baby_inform);
        l_stud=(LinearLayout)findViewById(R.id.l_student_inform);
        l_adult=(LinearLayout)findViewById(R.id.l_adult_inform);
        l_middle=(LinearLayout)findViewById(R.id.l_middle_inform);
        l_old=(LinearLayout)findViewById(R.id.l_old_inform);
        l_disa=(LinearLayout)findViewById(R.id.l_disable_inform);
        l_one=(LinearLayout)findViewById(R.id.l_oneparent_inform);
        l_multi=(LinearLayout)findViewById(R.id.l_multiculture_inform);
        l_low=(LinearLayout)findViewById(R.id.l_lowmoney_inform);

        tv_preg=(TextView)findViewById(R.id.tv_pregnant_inform);
        tv_baby=(TextView)findViewById(R.id.tv_baby_inform);
        tv_stud=(TextView)findViewById(R.id.tv_student_inform);
        tv_adult=(TextView)findViewById(R.id.tv_adult_inform);
        tv_middle=(TextView)findViewById(R.id.tv_middle_inform);
        tv_old=(TextView)findViewById(R.id.tv_old_inform);
        tv_disa=(TextView)findViewById(R.id.tv_disable_inform);
        tv_one=(TextView)findViewById(R.id.tv_oneparent_inform);
        tv_multi=(TextView)findViewById(R.id.tv_multiculture_inform);
        tv_low=(TextView)findViewById(R.id.tv_lowmoney_inform);

        i_preg=(ImageView)findViewById(R.id.i_pregnant_inform);
        i_baby=(ImageView)findViewById(R.id.i_baby_inform);
        i_stud=(ImageView)findViewById(R.id.i_student_inform);
        i_adult=(ImageView)findViewById(R.id.i_adult_inform);
        i_middle=(ImageView)findViewById(R.id.i_middle_inform);
        i_old=(ImageView)findViewById(R.id.i_old_inform);
        i_disa=(ImageView)findViewById(R.id.i_disable_inform);
        i_one=(ImageView)findViewById(R.id.i_oneparent_inform);
        i_multi=(ImageView)findViewById(R.id.i_multiculture_inform);
        i_low=(ImageView)findViewById(R.id.i_lowmoney_inform);

        gender_group=(RadioGroup)findViewById(R.id.radio_group_inform);
        gender_w=(RadioButton)findViewById(R.id.rg_btn1_inform);
        gender_m=(RadioButton)findViewById(R.id.rg_btn2_inform);

        l_preg.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(preg){
                    tv_preg.setTextColor(Color.parseColor("#484848"));
                    i_preg.setImageResource(R.drawable.pregnancy_mint);
                    l_preg.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    preg=false;
                }else{
                    tv_preg.setTextColor(Color.parseColor("#ffffff"));
                    i_preg.setImageResource(R.drawable.pregnancy_white);
                    l_preg.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    preg=true;
                }

            }
        });

        l_baby.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(baby){
                    tv_baby.setTextColor(Color.parseColor("#484848"));
                    i_baby.setImageResource(R.drawable.baby_mint);
                    l_baby.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    baby=false;
                }else{
                    tv_baby.setTextColor(Color.parseColor("#ffffff"));
                    i_baby.setImageResource(R.drawable.baby_white);
                    l_baby.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    baby=true;
                }

            }
        });

        l_stud.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(stud){
                    tv_stud.setTextColor(Color.parseColor("#484848"));
                    i_stud.setImageResource(R.drawable.teen_mint);
                    l_stud.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    stud=false;
                }else{
                    tv_stud.setTextColor(Color.parseColor("#ffffff"));
                    i_stud.setImageResource(R.drawable.teen_white);
                    l_stud.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    stud=true;
                }

            }
        });

        l_adult.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(adult){
                    tv_adult.setTextColor(Color.parseColor("#484848"));
                    i_adult.setImageResource(R.drawable.adult_mint);
                    l_adult.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    adult=false;
                }else{
                    tv_adult.setTextColor(Color.parseColor("#ffffff"));
                    i_adult.setImageResource(R.drawable.adult_white);
                    l_adult.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    adult=true;
                }

            }
        });
        l_middle.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(middle){
                    tv_middle.setTextColor(Color.parseColor("#484848"));
                    i_middle.setImageResource(R.drawable.middle_mint);
                    l_middle.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    middle=false;
                }else{
                    tv_middle.setTextColor(Color.parseColor("#ffffff"));
                    i_middle.setImageResource(R.drawable.middle_white);
                    l_middle.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    middle=true;
                }

            }
        });
        l_old.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(old){
                    tv_old.setTextColor(Color.parseColor("#484848"));
                    i_old.setImageResource(R.drawable.old_mint);
                    l_old.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    old=false;
                }else{
                    tv_old.setTextColor(Color.parseColor("#ffffff"));
                    i_old.setImageResource(R.drawable.old_white);
                    l_old.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    old=true;
                }

            }
        });

        l_disa.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(disa){
                    tv_disa.setTextColor(Color.parseColor("#484848"));
                    i_disa.setImageResource(R.drawable.disabled_mint);
                    l_disa.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    disa=false;
                }else{
                    tv_disa.setTextColor(Color.parseColor("#ffffff"));
                    i_disa.setImageResource(R.drawable.disabled_white);
                    l_disa.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    disa=true;
                }

            }
        });

        l_one.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(one){
                    tv_one.setTextColor(Color.parseColor("#484848"));
                    i_one.setImageResource(R.drawable.oneparent_mint);
                    l_one.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    one=false;
                }else{
                    tv_one.setTextColor(Color.parseColor("#ffffff"));
                    i_one.setImageResource(R.drawable.oneparent_white);
                    l_one.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    one=true;
                }

            }
        });

        l_multi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(multi){
                    tv_multi.setTextColor(Color.parseColor("#484848"));
                    i_multi.setImageResource(R.drawable.multi_mint);
                    l_multi.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    multi=false;
                }else{
                    tv_multi.setTextColor(Color.parseColor("#ffffff"));
                    i_multi.setImageResource(R.drawable.multi_white);
                    l_multi.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    multi=true;
                }

            }
        });

        l_low.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(low){
                    tv_low.setTextColor(Color.parseColor("#484848"));
                    i_low.setImageResource(R.drawable.low_mint);
                    l_low.setBackground(getResources().getDrawable(R.drawable.r_corner_gray));
                    low=false;
                }else{
                    tv_low.setTextColor(Color.parseColor("#ffffff"));
                    i_low.setImageResource(R.drawable.low_white);
                    l_low.setBackground(getResources().getDrawable(R.drawable.r_corner_mint));
                    low=true;
                }

            }
        });


        // 가입 버튼 클릭
        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setData();
                finish();
            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("서울특별시");
        arrayList.add("부산광역시");
        arrayList.add("대구광역시");
        arrayList.add("인천광역시");
        arrayList.add("광주광역시");
        arrayList.add("대전광역시");
        arrayList.add("울산광역시");
        arrayList.add("세종특별자치시");
        arrayList.add("경기도");
        arrayList.add("강원도");
        arrayList.add("충청북도");
        arrayList.add("충청남도");
        arrayList.add("전라북도");
        arrayList.add("전라남도");
        arrayList.add("경상북도");
        arrayList.add("경상남도");
        arrayList.add("제주특별자치도");

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("강남구");
        arrayList2.add("강동구");
        arrayList2.add("강북구");
        arrayList2.add("강서구");
        arrayList2.add("관악구");
        arrayList2.add("광진구");
        arrayList2.add("구로구");
        arrayList2.add("금천구");
        arrayList2.add("노원구");
        arrayList2.add("도봉구");
        arrayList2.add("동대문구");
        arrayList2.add("동작구");
        arrayList2.add("마포구");
        arrayList2.add("서대문구");
        arrayList2.add("서초구");
        arrayList2.add("성동구");
        arrayList2.add("성북구");
        arrayList2.add("송파구");
        arrayList2.add("양천구");
        arrayList2.add("영등포구");
        arrayList2.add("용산구");
        arrayList2.add("은평구");
        arrayList2.add("종로구");
        arrayList2.add("중구");
        arrayList2.add("중랑구");

        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1_inform);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner1.setAdapter(arrayAdapter);

        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2_inform);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList2);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setSelection(5);

    }
}
