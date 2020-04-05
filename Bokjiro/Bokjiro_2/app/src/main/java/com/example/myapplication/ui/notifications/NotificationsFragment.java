package com.example.myapplication.ui.notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.RecAdapter;
import com.example.myapplication.dumy_serviList;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private ListView listview;
    private RecAdapter adapter;
    ArrayList<String> noti_list;

    private int[] images = {R.drawable.heart2,R.drawable.preg,R.drawable.milk,R.drawable.medicine};
//    private String[] titles = {"산모.신생아 건강관리 지원", "저소득층 기저귀.조제분유 지원", "고위험 임산부 의료비 지원"};
//    private String[] durs = {"2019.11.05 - 2020.01.14","2019.12.15 - 2020.06.15","2019.12.15 - 2020.02.13"};
//    private String[] days = {"D-30","D-183","D-60"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        adapter = new RecAdapter();
        listview = (ListView)root.findViewById(R.id.list);
        listview.setAdapter(adapter);
        noti_list = new ArrayList<String>();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-mm-dd");

        noti_list=getStringArrayPref(getContext(),"notiList");
        if (noti_list != null) {
            for (String value : noti_list) {
                Log.d("testtestnotiList : ",value);
                adapter.addItem(ContextCompat.getDrawable(this.getContext(),images[0]),value,"등록일자 : "+(simpleDate.format(new Date())),"(상시접수)");
            }
        }

//        listview.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                return false;
//            }
//        });
        listview.setOnItemLongClickListener( new ListViewItemLongClickListener() );

        return root;
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
    private class ListViewItemLongClickListener implements AdapterView.OnItemLongClickListener
    {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("알림해제");
            builder.setMessage("알림을 해제 하시겠습니까?");
            builder.setNegativeButton("아니오",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.setPositiveButton("예",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(),"해당 알림이 해제되었습니다.",Toast.LENGTH_SHORT).show();
                            noti_list.remove(position);
                            setStringArrayPref(getContext(),"notiList",noti_list);
                            listview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                    });
            builder.show();
            listview.setAdapter(adapter);
            return true;
        }


    }
}