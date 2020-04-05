package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.android.GsonFactory;
import ai.api.android.AIDataService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean isPageopen =false;
    LinearLayout slide_page;
    TextView tv_name;
    CheckBox cb_1,cb_2,cb_3,cb_4,cb_n;
    RadioGroup radioGroup,radioGroup2,radioGroup3;RadioButton rad_fe,rad_ma,rad_pat_o,rad_pat_x,rad_dis_o,rad_dis_x;
    ImageView menu_img;
    SharedPreferences pref;SharedPreferences.Editor editor;
    String genders,name,loc;
    BottomNavigationView navView;
    Spinner spinner1,spinner2,spinner3;
    ArrayAdapter arrayAdapter,arrayAdapter2,arrayAdapter3;
    EditText edtage;
    int age, freg, baby, kid, chung, jung, no,low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref=getSharedPreferences("pref", Context.MODE_PRIVATE);
        initialize();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        getSupportActionBar().hide();
        setSpinner();
        setUser();


        menu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageopen==false){// 페이지가 닫혀있으면
                    menu_img.setImageResource(R.drawable.arrow3);
                    slide_page.setVisibility(View.VISIBLE);
                    isPageopen=true;
                }
                else {
                    menu_img.setImageResource(R.drawable.menu3);
                    slide_page.setVisibility(View.GONE);
                    isPageopen=false;
                }
            }
        });
        //show();
    }

    public void initialize(){
        spinner1 = findViewById(R.id.spinner_inform1);spinner2 = findViewById(R.id.spinner_inform2);spinner3 = findViewById(R.id.spinner_inform3);
        slide_page = findViewById(R.id.slide_page);
        edtage = findViewById(R.id.edtage);
        tv_name = findViewById(R.id.tv_name);
        cb_1=findViewById(R.id.cb_handi);cb_2=findViewById(R.id.cb_hanbumo);
        cb_3 = findViewById(R.id.cb_damunhwa);cb_4=findViewById(R.id.cb_lowsodek);cb_n=findViewById(R.id.cb_none);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup2 = findViewById(R.id.radiogroup2);
        radioGroup3 = findViewById(R.id.radiogroup3);
        rad_fe = findViewById(R.id.rad_fe);rad_ma = findViewById(R.id.rad_ma);
        rad_dis_o = findViewById(R.id.rad_dis_o);rad_dis_x = findViewById(R.id.rad_dis_x);
        rad_pat_o = findViewById(R.id.rad_pat_o);rad_pat_x=findViewById(R.id.rad_pat_x);
        navView = findViewById(R.id.nav_view);
        menu_img =findViewById(R.id.bar_menu);
    }

    public void setSpinner(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("서울특별시");arrayList.add("부산광역시");arrayList.add("대구광역시");
        arrayList.add("인천광역시");arrayList.add("광주광역시");arrayList.add("대전광역시");
        arrayList.add("울산광역시");arrayList.add("세종특별자치시");arrayList.add("경기도");
        arrayList.add("강원도");arrayList.add("충청북도");arrayList.add("충청남도");
        arrayList.add("전라북도");arrayList.add("전라남도");arrayList.add("경상북도");
        arrayList.add("경상남도");arrayList.add("제주특별자치도");

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("강남구");arrayList2.add("강동구");arrayList2.add("강북구");
        arrayList2.add("강서구");arrayList2.add("관악구");arrayList2.add("광진구");
        arrayList2.add("구로구");arrayList2.add("금천구");arrayList2.add("노원구");
        arrayList2.add("도봉구");arrayList2.add("동대문구");arrayList2.add("동작구");
        arrayList2.add("마포구");arrayList2.add("서대문구");arrayList2.add("서초구");
        arrayList2.add("성동구");arrayList2.add("성북구");arrayList2.add("송파구");
        arrayList2.add("양천구");arrayList2.add("영등포구");arrayList2.add("용산구");
        arrayList2.add("은평구");arrayList2.add("종로구");arrayList2.add("중구");arrayList2.add("중랑구");

        ArrayList<String> arrayList3 = new ArrayList<>();
        arrayList3.add("임신‧출산");arrayList3.add("영유아");
        arrayList3.add("아동 청소년");arrayList3.add("청년");
        arrayList3.add("중장년");arrayList3.add("노년");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner1.setAdapter(arrayAdapter);
        arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList2);
        spinner2.setAdapter(arrayAdapter2);
        arrayAdapter3 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList3);
        spinner3.setAdapter(arrayAdapter3);
    }

    public void setUser(){
        name = pref.getString("name","데이터가 없어요");
        name = name.substring(1, name.length());
        name = name.substring(0,name.length()-1);
        String str_name = name+"님 맞춤정보";
        tv_name.setText(str_name);
        //나이
        age = pref.getInt("age",0);
        String str = Integer.toString(age);
        edtage.setText(str);
        //성별
        int gender = pref.getInt("gender",2);
        if(gender == 0){
            rad_fe.setChecked(true);
        }else if(gender==1){
            rad_ma.setChecked(true);
        }

        //거주지
        loc = pref.getString("loc","");
        loc = loc.substring(1, loc.length());
        loc = loc.substring(0, loc.length()-1);
        int index = loc.indexOf(" "); //공백 위치
        String state = loc.substring(0,index);
        if(state.contains("서울")){
            spinner1.setSelection(0);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("부산")){
            spinner1.setSelection(1);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("대구")){
            spinner1.setSelection(2);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("인천")){
            spinner1.setSelection(3);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("광주")){
            spinner1.setSelection(4);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("대전")){
            spinner1.setSelection(5);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("울산")){
            spinner1.setSelection(6);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("세종")){
            spinner1.setSelection(7);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("경기")){
            spinner1.setSelection(8);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("강원")){
            spinner1.setSelection(9);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("충청북")){
            spinner1.setSelection(10);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("충청남")){
            spinner1.setSelection(11);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.contains("전라북")){
            spinner1.setSelection(12);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.equals("전라남")){
            spinner1.setSelection(13);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.equals("경상북")){
            spinner1.setSelection(14);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.equals("경상남")){
            spinner1.setSelection(15);
            arrayAdapter.notifyDataSetChanged();
        }else if(state.equals("제주")){
            spinner1.setSelection(16);
            arrayAdapter.notifyDataSetChanged();
        }

        String town = loc.substring(index+1,loc.length());
        if(town.contains("강남")){
            spinner2.setSelection(0);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("강동")){
            spinner2.setSelection(1);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("강북")){
            spinner2.setSelection(2);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("강서")){
            spinner2.setSelection(3);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("관악")){
            spinner2.setSelection(4);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("광진")){
            spinner2.setSelection(5);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("구로")){
            spinner2.setSelection(6);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("금천")){
            spinner2.setSelection(7);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("노원")){
            spinner2.setSelection(8);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("도봉")){
            spinner2.setSelection(9);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("동대문")){
            spinner2.setSelection(10);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("동작")){
            spinner2.setSelection(11);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("마포")){
            spinner2.setSelection(12);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("서대문")){
            spinner2.setSelection(13);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("서초")){
            spinner2.setSelection(14);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("성동")){
            spinner2.setSelection(15);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("성북")){
            spinner2.setSelection(16);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("송파")){
            spinner2.setSelection(17);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("양천")){
            spinner2.setSelection(18);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("영등포")){
            spinner2.setSelection(19);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("용산")){
            spinner2.setSelection(20);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("은평")){
            spinner2.setSelection(21);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("종로")){
            spinner2.setSelection(22);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("중구")){
            spinner2.setSelection(23);
            arrayAdapter2.notifyDataSetChanged();
        }else if(town.contains("중랑")){
            spinner2.setSelection(24);
            arrayAdapter2.notifyDataSetChanged();
        }

        //생애주기
        freg = pref.getInt("freg",2);
        baby = pref.getInt("baby",2);
        kid = pref.getInt("kid",2);
        chung = pref.getInt("chung",2);
        jung = pref.getInt("jung",2);
        no = pref.getInt("no",2);
        if(freg==1){
            spinner3.setSelection(0);
            arrayAdapter3.notifyDataSetChanged();
        }else if(baby==1){
            spinner3.setSelection(1);
            arrayAdapter3.notifyDataSetChanged();
        }else if(kid==1){
            spinner3.setSelection(2);
            arrayAdapter3.notifyDataSetChanged();
        }else if(chung==1){
            spinner3.setSelection(3);
            arrayAdapter3.notifyDataSetChanged();
        }else if(jung==1){
            spinner3.setSelection(4);
            arrayAdapter3.notifyDataSetChanged();
        }else if(no==1){
            spinner3.setSelection(5);
            arrayAdapter3.notifyDataSetChanged();
        }

        //가구상황
        int handi = pref.getInt("handi",2);
        if(handi ==1)
            cb_1.setChecked(true);
            rad_pat_o.setChecked(true);
        int hanbumo = pref.getInt("hanbumo",2);
        if(hanbumo == 1)
            cb_2.setChecked(true);
        int damunhwa = pref.getInt("damunhwa",2);
        if(damunhwa == 1)
            cb_3.setChecked(true);
        String lowsodek = pref.getString("lowsodek","");
        Log.d("lowwww",lowsodek);
        if(lowsodek.contains("1")){
            cb_4.setChecked(true);
            low = 1;
        }else if(lowsodek.contains("0")){
            low = 0;
        }
        if((handi==0)&&(hanbumo==0)&&(damunhwa==0)&&(low==0)){
            cb_n.setChecked(true);
        }

        //장애여부
        if(handi == 1){
            rad_dis_o.setChecked(true);
        }else if(handi ==0){
            rad_dis_x.setChecked(true);
        }

        //보훈대상여부
        String bohun = pref.getString("bohun","");
        if(bohun.contains("1")){
            rad_pat_o.setChecked(true);
        }else if(bohun.contains("0")){
            rad_pat_x.setChecked(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.
//        if (id == android.R.id.home) {
//            Toast.makeText(this, "홈아이콘 클릭", Toast.LENGTH_SHORT).show();
//            return true;
//        }
        Intent intent = new Intent(this, inform.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    //회원가입 페이지로 이동
    public void show(){
        Intent intent = new Intent(getApplicationContext(), join.class);
        startActivity(intent);
        //finish();

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("회원등록");
        builder.setMessage("저장된 회원 정보가 없습니다! 정보를 입력해주세요");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"예",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), join.class);
                        startActivity(intent);
                    }
                });
        builder.show();
        */

    }
    private class SlidingPageAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageopen){
                slide_page.setVisibility(View.INVISIBLE);
                menu_img.setImageResource(R.drawable.arrow_left);
                isPageopen=false;
            }
            else{
                slide_page.setVisibility(View.VISIBLE);
                menu_img.setImageResource(R.drawable.menu2);
                isPageopen=true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    class tolkAdapter extends BaseAdapter {

        ArrayList<Tolk_item> items = new ArrayList<>();
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
        public void addItem(Tolk_item item){
            items.add(item);
        }
        @Override
        public View getView(int i, View v, ViewGroup viewGroup) {
            TolkItemView view = new TolkItemView(getApplicationContext());
            Tolk_item item = items.get(i);
            if(item.isBot()==false){
                view.setBackGround2();
                view.setTolk(item.getTolk());
            }else{
                view.setBackGround1();
                Toast.makeText(getApplicationContext(),item.getItems().length+"",Toast.LENGTH_SHORT).show();

                if(item.getItems().length>1){
                    view.createButton(item.getItems());
                    view.setTolk(item.getItems()[0]);
                }else{
                    view.setTolk(item.getTolk());
                }
            }
            return view;
        }
    }
}
