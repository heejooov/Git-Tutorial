package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginActivity extends Activity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button btnLogin;
    EditText edtId,edtPw;
    ArrayList<String> list;
    TextView tv_name,tv_join;
    LinearLayout slide_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        list = new ArrayList<String>();
        btnLogin = findViewById(R.id.btnLogin);
        edtId = findViewById(R.id.loginId);
        edtPw = findViewById(R.id.loginPw);
        tv_name = findViewById(R.id.tv_name);
        tv_join=findViewById(R.id.textViewforJoin);
        pref=getSharedPreferences("pref", Context.MODE_PRIVATE);

        //서버에서 회원정보 가져온 후 메인페이지로 이동
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtId.getText().toString().trim();
                if(edtId.getText().toString().equals("jandi1")){
                    id = "001";
                }if(edtId.getText().toString().equals("chacha")){
                    id = "002";
                }if(edtId.getText().toString().equals("hyeri99")){
                    id = "003";
                }if(edtId.getText().toString().equals("jina007")){
                    id = "004";
                }if(edtId.getText().toString().equals("sunghun")){
                    id = "005";
                }if(edtId.getText().toString().equals("jack0")){
                    id = "006";
                }
                getUserInfo task=new getUserInfo();
                task.execute(id);
            }
        });

        //회원 가입 페이지로 이동
        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), join.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    //회원정보 가져오기
    public class getUserInfo extends AsyncTask<String,Void,String> {
            OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            //finish();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "http://52.79.72.52:5000/signin";
            String userid = params[0];
            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
            urlBuilder.addEncodedQueryParameter("uid", userid);
            String requestUrl = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) { }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
                        String data = responseBodyCopy.string();
                        data = data.replaceAll("[\\[\\]]", "");
                        Log.d("Data",data);
                        //response.body().close();

                        StringTokenizer st = new StringTokenizer(data,",");
                        while(st.hasMoreTokens()){
                            String token=st.nextToken();
                            list.add(token);
                        }

                        int uid = Integer.parseInt(list.get(0).toString());
                        String name = list.get(1).toString();
                        int age = Integer.parseInt(list.get(2).toString());
                        int gender = Integer.parseInt(list.get(3).toString());
                        String loc = list.get(4).toString();
                        int freg = Integer.parseInt(list.get(5).toString());
                        int baby = Integer.parseInt(list.get(6).toString());
                        int kid = Integer.parseInt(list.get(7).toString());
                        int chung = Integer.parseInt(list.get(8).toString());
                        int jung = Integer.parseInt(list.get(9).toString());
                        int no = Integer.parseInt(list.get(10).toString());
                        int handi = Integer.parseInt(list.get(11).toString());
                        int hanbumo = Integer.parseInt(list.get(12).toString());
                        int damunhwa = Integer.parseInt(list.get(13).toString());
                        String low = list.get(14).toString();
                        String bohun = list.get(15).toString();
                        Log.d("lowsodek",low);

                        editor = pref.edit();
                        editor.putInt("uid",uid);editor.putString("name",name);editor.putInt("age",age);editor.putInt("gender",gender);editor.putString("loc",loc);
                        editor.putInt("freg",freg);editor.putInt("baby",baby);editor.putInt("kid",kid);editor.putInt("chung",chung);
                        editor.putInt("jung",jung);editor.putInt("no",no);editor.putInt("handi",handi);
                        editor.putInt("hanbumo",hanbumo);editor.putInt("damunhwa",damunhwa);editor.putString("lowsodek",low);editor.putString("bohun",bohun);
                        editor.apply();

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            });

            return null;
        }
    }
}
