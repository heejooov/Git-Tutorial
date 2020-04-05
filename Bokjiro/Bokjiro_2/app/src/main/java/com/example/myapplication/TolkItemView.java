package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TolkItemView extends LinearLayout {
    TextView tolkView;
    LinearLayout tolkView_layout,button_layout,list_layout;
    ArrayList<Button> buttons = new ArrayList<>();
    LayoutParams params = new LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    LinearLayout ll = new LinearLayout(getContext());
    ArrayList<LinearLayout> resp=new ArrayList<>();
    LayoutParams paramm=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    Image image_pre;
    Button chat_detail;

    TextView tv1,tv2,tv3,tv4,tv5,tv6;

    //ui만들기용
    LinearLayout dumy_visible;

    public TolkItemView(Context context) {
        super(context);
        init(context);
    }
    public TolkItemView(Context context, AttributeSet attrs) {
        super(context);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tolk_item, this, true);
        tolkView = (TextView) findViewById(R.id.tolk_view);
        tolkView_layout = (LinearLayout)findViewById(R.id.tolk_view_layout);
        button_layout = (LinearLayout)findViewById(R.id.button_layout);
        list_layout=(LinearLayout)findViewById(R.id.list_layout);
        dumy_visible= (LinearLayout)findViewById(R.id.dumy_visible);
        chat_detail=(Button)findViewById(R.id.chat_detail);
        chat_detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), dumy_serviList.class);
                getContext().startActivity(intent);
            }
        });

        tv1 = (TextView) findViewById(R.id.tolk_sv1);
        tv2 = (TextView) findViewById(R.id.tolk_svd1);
        tv3 = (TextView) findViewById(R.id.tolk_sv2);
        tv4 = (TextView) findViewById(R.id.tolk_svd2);
        tv5 = (TextView) findViewById(R.id.tolk_sv3);
        tv6 = (TextView) findViewById(R.id.tolk_svd3);
    }
    public void setTolk(String tolk) { // 톡 내용 채우기
        tolkView.setText(tolk);
    }
    public void createButton(String[] btns){
        // linearLayout params 정의
        if(btns.length>1){
            for(int j=1;j<btns.length;j++){
                ll.setOrientation(LinearLayout.VERTICAL);
                Button b=new Button(getContext());
                b.setText(btns[j]);
                b.setId((int)j);
                b.setLayoutParams(params);
                final int position = j;
                b.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("log", "position :" + position);
                        Toast.makeText(getContext(), tolkView.getText().toString().split(" ")[0]+ ":" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                //버튼 add
                ll.addView(b);
                //LinearLayout 정의된거 add
            }
            button_layout.addView(ll);
        }




//                buttons.add(b);
//                this.createButton(btns);
           // buttons.clear();
//            for(int i=1; i<btns.length;i++){
//                Button b=new Button(this.getContext());
//                b.setText(btns[i]);
//                b.setId(i);
//                b.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(),tolkView.getText()+"  "+view.getId(),Toast.LENGTH_LONG).show();
//                    }
//                });
//                buttons.add(b);
//            }

    }

    public void creatImg(String ss){

        if((ss.substring(0,1).equals("!"))==false) {// 질문을 제대로 알아듣고 소분류로 물어본 경우
            changeCode apiapi=new changeCode();
            String[][] str=apiapi.callApi(ss);

            tv1.setText(str[0][0]);
            if(str[0][1].length()>25)tv2.setText(str[0][1].substring(0,25)+"...");
            else tv2.setText(str[0][1]);
            tv3.setText(str[1][0]);
            if(str[1][1].length()>25)tv4.setText(str[1][1].substring(0,25)+"...");
            else tv4.setText(str[1][1]);
            tv5.setText(str[2][0]);
            if(str[2][1].length()>25)tv6.setText(str[2][1].substring(0,25)+"...");
            else tv6.setText(str[2][1]);
            dumy_visible.setVisibility(View.VISIBLE);
        }else{
            tolkView.setText(ss);
            dumy_visible.setVisibility(View.GONE);
        }
    }

//    public void creatImg(String[] strs){
//        paramm.topMargin=15;
//        paramm.leftMargin=10;
//        for(int i=0;i<strs.length;i=i+2){
//            LinearLayout linearLayout=new LinearLayout(getContext());
//            linearLayout.setLayoutParams(paramm);
//            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//            linearLayout.setPadding(8,6,12,6);
//            ImageView iv=new ImageView(getContext());
//            iv.setImageResource(R.drawable.pregnancy_white);
//            linearLayout.addView(iv);
//            LinearLayout ll2=new LinearLayout(getContext());
//            ll2.setOrientation(LinearLayout.VERTICAL);
//            TextView tv1=new TextView(getContext());
//            tv1.setText(strs[i]);
//            tv1.setTextSize(18);
//            tv1.setTextColor(Color.WHITE);
//            tv1.setPadding(0,10,0,10);
//
//            TextView tv2=new TextView(getContext());
//            tv2.setText(strs[i+1]);
//            tv2.setTextSize(12);
//            ll2.addView(tv1);
//            ll2.addView(tv2);
//            linearLayout.addView(ll2);
//
//
//            linearLayout.setBackgroundResource(R.drawable.round_background1);
//            list_layout.addView(linearLayout);
//
//            dumy_visible.setVisibility(View.VISIBLE);
//        }
//
//    }
    public void setBackGround1() { //챗봇이 말할 때
        tolkView.setBackgroundResource(R.drawable.round_background1);
    }
    public void setBackGround2() {// 내가 말할 때
        tolkView.setBackgroundResource(R.drawable.round_background2);
        tolkView.setTextColor(Color.BLACK);
        tolkView_layout.setGravity(Gravity.RIGHT);

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        params.gravity = Gravity.RIGHT;
//        tolkView.setLayoutParams(params);
        dumy_visible.setVisibility(View.GONE);
    }
}
