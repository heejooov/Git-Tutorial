package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class dumy_serviList extends AppCompatActivity {

    ImageView svLinst_menu;
    ListView listview;
    ArrayList<String> noti_list;
    private serviceAdapter serviAdapter;
    String key;
    changeCode apiapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dumy_servi_list);
        getSupportActionBar().hide(); // 앱바 숨겨야 함 지우지 마세요

        svLinst_menu = (ImageView)findViewById(R.id.svLinst_menu);
        svLinst_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listview = (ListView)findViewById(R.id.more_List);
        serviAdapter = new serviceAdapter();
        //noti_list = new ArrayList<String>();
        noti_list=getStringArrayPref(this,"notiList");
        if(noti_list==null){
            noti_list = new ArrayList<String>();
        }

//        serviAdapter.addItem(new moreList_item("title","content","아동",true));
//        listview.setAdapter(serviAdapter);

        SharedPreferences sp=getApplication().getSharedPreferences("Skey",MODE_PRIVATE);
        String key=sp.getString("text","");
        //key = "아동";
        if(key!=""||key!=null){
            add_ListItem(key);
        }
        listview.setAdapter(serviAdapter);

    }
    //리스트 더하기
    public void add_ListItem(String key){
        changeCode apiapi=new changeCode();
        String[][] str=apiapi.callApi(key);
        for(int i=0;i<20;i++){
            if(str[i]!=null&&str[i][0]!=null){
                serviAdapter.addItem(new moreList_item(str[i][0],str[i][1],str[i][2],true));
            }
        }
    }
    //SharedPreferences에 JSON으로 저장하기
    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }
    //SharedPreferences에 있는 JSON값을 읽어오기
    private ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }


    //리스트 뷰 어댑터 클래스
    class serviceAdapter extends BaseAdapter {
        ArrayList<moreList_item> items = new ArrayList<>();
        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public Object getItem(int i) {
            return items.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        public void addItem(moreList_item item){
            items.add(item);
        }
        @Override
        public View getView(int i, View v, ViewGroup viewGroup) {
            MoreItemView view = new MoreItemView(getApplicationContext());
            moreList_item item = items.get(i);
            final String fiText = item.getMore_title();
            final ImageView fiImgeView = view.more_noti;

            view.setMore_title(item.getMore_title());
            view.setMore_content(item.getMore_content());
            view.setService_Id(item.getMore_id());
            //이미지 변경은 일단 생략
            if(item.isNoti()==false){ //알림설정 없을때
                view.setMore_noti(false);
            }else{//알림설정 있을때
                view.setMore_noti(true);
            }

            view.more_noti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dumy_serviList.this);
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
                                Toast.makeText(getApplicationContext(),"알림이 등록되었습니다.",Toast.LENGTH_SHORT).show();
                                noti_list.add(fiText);
                                setStringArrayPref(getApplicationContext(),"notiList",noti_list);
                                fiImgeView.setImageResource(R.drawable.icon_noti3);
                            }
                        });
                builder.show();
            }
        });

            return view;
        }
    }
}
