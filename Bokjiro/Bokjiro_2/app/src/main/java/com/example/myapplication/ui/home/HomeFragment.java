package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TolkItemView;
import com.example.myapplication.Tolk_item;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AIListener{

    private HomeViewModel homeViewModel;
    AIRequest aiRequest;
    AIDataService aiDataService;
    AIService aiService;
    private EditText inputText;
    private Button sendButton;
    String TAG = "TEST";

    ListView listview;
    private tolkAdapter tolkAdapter;

    @Override
    public void onResult(AIResponse response) {
        Log.d("finished..",response.toString());
        Result result= response.getResult();
        //listview.setAdapter(tolkAdapter);
    }

    @Override
    public void onError(AIError error) { }

    @Override
    public void onAudioLevel(float level) { }

    @Override
    public void onListeningStarted() { }

    @Override
    public void onListeningCanceled() { }

    @Override
    public void onListeningFinished() {
        listview.setAdapter(tolkAdapter);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //DialogFlow 연결
        final AIConfiguration config = new AIConfiguration("985d11475d39439a969216a0f1626bc1",
                AIConfiguration.SupportedLanguages.Korean,
                AIConfiguration.RecognitionEngine.System);
        aiDataService = new AIDataService(getContext(),config);
        aiService = AIService.getService(getContext(), config);
        aiRequest = new AIRequest();
        aiService.setListener(this);

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        inputText = (EditText)root.findViewById(R.id.inputText);
        sendButton = (Button)root.findViewById(R.id.sendButton);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        // 리스트 뷰 톡이랑 연결
        listview = (ListView)root.findViewById(R.id.tolk_list);
        //데이터를 저장하게 되는 리스트
        List<String> list = new ArrayList<>();

        //리스트뷰의 어댑터를 지정해준다.
        tolkAdapter = new tolkAdapter();
        listview.setAdapter(tolkAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputText.getText().toString().trim();
                tolkAdapter.addItem(new Tolk_item(false,message));
                inputText.setText("");
                //resultTextView.setText("");
                sendRequest(message);
                listview.setAdapter(tolkAdapter);  //주석
                listview.setSelection(tolkAdapter.getCount() - 1);
            }
        });
        return root;
    }

    private void sendRequest(String message){
        aiRequest.setQuery(message);
        new AsyncTask<AIRequest, Void, AIResponse>() {
            @Override
            protected AIResponse doInBackground(AIRequest... aiRequests) {
                final AIRequest request = aiRequests[0];
                try {
                    final AIResponse response = aiDataService.request(request);
                    return response;
                } catch (AIServiceException e) {
                    AIError aiError = new AIError(e);
                    onError(aiError);
                }
                return null;
            }

            @Override
            protected void onPostExecute(AIResponse aiResponse) {
                if (aiResponse != null){
                    Result result = aiResponse.getResult();
                    String response = result.getFulfillment().getSpeech();
                    String intent = result.getMetadata().getIntentName();
                    Log.d(TAG,"Intent : "+intent);
                    //Toast.makeText(getContext(),"응답 : "+response,Toast.LENGTH_SHORT).show();
                    //String[] strs=response.split("-");
                   // String[] strs; 주석
                    // strs=response.split("-");  주석
                    makeResponse(response);

//                    if(response.substring(0,1)!="!"&& strs.length<2){// 질문을 제대로 알아듣고 소분류로 물어본 경우
//                        interest = new String[3][2];
//
//                        for(int i=0; i<3; i++){
//                            if(interest[i]==null){
//                                interest[0] = new String[2];
//                                interest[0][0]=strs[0];
//                            }
//                        }
//                    }
                    //listview.setAdapter(tolkAdapter);
                    //listview.setSelection(tolkAdapter.getCount() - 1);
                    //resultTextView.setText(result.getFulfillment().getDisplayText());
                }else{

                    Log.d(TAG,"airequest is null");
                    tolkAdapter.addItem(new Tolk_item(true,"아아 Test"));
                    listview.setAdapter(tolkAdapter);
                    listview.setSelection(tolkAdapter.getCount() - 1);

                }
            }
        }.execute(aiRequest);
    }
    private void makeResponse(String msg){

        SharedPreferences sp=getContext().getSharedPreferences("Skey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        Log.d("strss",msg);
        editor.remove("text");
        editor.putString("text",msg);
        editor.commit();

        tolkAdapter.addItem(new Tolk_item(true,msg));
//        tolkAdapter.addItem(new Tolk_item(true,msg));
        listview.setAdapter(tolkAdapter);
        listview.setSelection(tolkAdapter.getCount() - 1);
    }
    //리스트 뷰 어댑터 클래스
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
            TolkItemView view = new TolkItemView(getContext());
            Tolk_item item = items.get(i);
            if(item.isBot()==false){
                view.setBackGround2();
                view.setTolk(item.getTolk());
            }else{
                //view.setBackGround1();
                //Toast.makeText(getApplicationContext(),item.getItems().length+"",Toast.LENGTH_SHORT).show();
//                if(item.getItems()!=null&&item.getItems().length>1){
//                    //view.createButton(item.getItems());
//                    view.creatImg(item.getItems());
//                    // view.setTolk(item.getItems()[0]);
//                }else{
//                    view.setTolk(item.getTolk());
//                }
                view.creatImg(item.getTolk());
            }

            return view;
        }
    }
}