package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


public class MoreItemView extends LinearLayout {
    TextView more_title,more_content;
    ImageView more_image, more_noti;
    String service_Id;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public String getService_Id() {
        return service_Id;
    }

    public void setService_Id(String service_Id) {
        this.service_Id = service_Id;
    }

    public MoreItemView(Context context) {
        super(context);
        init(context);

    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.moreitem, this, true);
        more_title = (TextView)findViewById(R.id.more_title);
        more_content = (TextView)findViewById(R.id.more_content);
        more_image = (ImageView) findViewById(R.id.more_image);
        more_noti = (ImageView) findViewById(R.id.more_noti);
        more_title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),dummy_serviDetail.class);
                intent.putExtra("service_key",service_Id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().getApplicationContext().startActivity(intent);
            }
        });
        more_content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),dummy_serviDetail.class);
                intent.putExtra("service_key",service_Id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().getApplicationContext().startActivity(intent);
            }
        });
//        more_noti.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setTitle("알림등록");
//                builder.setMessage("알림등록이 가능합니다! 하시겠습니까?");
//                builder.setNegativeButton("아니오",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        });
//                builder.setPositiveButton("예",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                //Toast.makeText(getContext(),"알림이 등록되었습니다.",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                builder.show();
//            }
//        });
    }

    public void setMore_title(String more_title) {
        this.more_title.setText(more_title);
    }
    public void setMore_content(String more_content) {
        this.more_content.setText(more_content);
    }
    public void setMore_image(Drawable img) {
        this.more_image.setImageDrawable(img);
    }

    public void setMore_noti(boolean noti) {
        if(noti==true){ // 알람 노란색
            more_noti.setVisibility(View.VISIBLE);
        }else{
            more_noti.setVisibility(View.INVISIBLE);
        }
    }
        public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext().getApplicationContext());
        builder.setTitle("알림등록");
        builder.setMessage("알림등록이 가능합니다! 하시겠습니까?");
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendNoti task = new sendNoti();
                        task.execute();

                        //Toast.makeText(getContext(),"알림이 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    }
        });
        builder.show();
    }

    public class sendNoti extends AsyncTask<String,Void,String>{

        String url = "http://172.30.1.8:8080/android";



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


}
