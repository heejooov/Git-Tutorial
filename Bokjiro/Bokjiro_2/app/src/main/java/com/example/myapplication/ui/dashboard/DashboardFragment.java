package com.example.myapplication.ui.dashboard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;

import com.example.myapplication.Bokji;
import com.example.myapplication.BokjiRetrofit;
import com.example.myapplication.ItemAdapter;
import com.example.myapplication.ListActivity;
import com.example.myapplication.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    @NotNull
    private ArrayList items;
    @NotNull
    private String cate_mid;
    private String cate_low;
    private boolean demotest;
    @NotNull
    public ItemAdapter adap;
    @NotNull
    private Handler mainHandler;
    private HashMap _$_findViewCache;
    RecyclerView recyclerView;
    private Button bt_show;
    View root;

    LinearLayout dumi_money;

    @NotNull
    public final ArrayList getItems(){
        return this.items;
    }

    public final void setItems(@NotNull ArrayList var1){
        this.items = var1;
    }

    @NotNull
    public final String getCate_mid(){return this.cate_mid;}

    public final void setCate_mid( String var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.cate_mid = var1;
    }


    @NotNull
    public final String getCate_low() {
        return this.cate_low;
    }

    public final void setCate_low(@NotNull String var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.cate_low = var1;
    }

    public final boolean getDemotest() {
        return this.demotest;
    }

    public final void setDemotest(boolean var1) {
        this.demotest = var1;
    }

    @NotNull
    public final ItemAdapter getAdap() {
        ItemAdapter var10000 = this.adap;
        if (this.adap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adap");
        }

        return var10000;
    }

    public final void setAdap(@NotNull ItemAdapter var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.adap = var1;
    }

    @NotNull
    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    public final void setMainHandler(@NotNull Handler var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.mainHandler = var1;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Intent intent = new Intent(getActivity(),ListActivity.class);
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.tv_money);
        bt_show = root.findViewById(R.id.bt_show);
        this.adap = new ItemAdapter(getContext(), this.items);
        if (this.adap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adap");
        }
        RecyclerView var10000 = (RecyclerView)root.findViewById(R.id.list1);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "list1");
        ItemAdapter var10001 = this.adap;
        var10000.setAdapter((RecyclerView.Adapter)var10001);

        dumi_money = (LinearLayout)root.findViewById(R.id.dumi_money);
        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkExpressionValueIsNotNull(textView, "tv_money");
                animateTextView(0,3740000,textView);
                dumi_money.setVisibility(View.VISIBLE);
            }
        });



        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public final void animateTextView(int initialValue, int finalValue, @NotNull final TextView textview) {
        Intrinsics.checkParameterIsNotNull(textview, "textview");
        ValueAnimator valueAnimator = ValueAnimator.ofInt(new int[]{initialValue, finalValue});
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "valueAnimator");
        valueAnimator.setDuration(1500L);
        valueAnimator.addUpdateListener((ValueAnimator.AnimatorUpdateListener)(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TextView var10000 = textview;
                Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "valueAnimator");
                var10000.setText((CharSequence)valueAnimator.getAnimatedValue().toString());
            }
        }));
        valueAnimator.start();
    }

    public final void connect() {
        Call bokjilist = (new BokjiRetrofit(this.cate_mid, this.cate_low)).getResponse();
        Log.e("main", "call connect ");
    }

    public final ArrayList getNewData() {
        Log.d("getNewData()", "getNewData()");
        boolean var1;
        if (this.demotest) {
            var1 = false;
            return new ArrayList();
        } else if (this.items.size() != 0) {
            this.connect();
            return this.items;
        } else {
            var1 = false;
            return new ArrayList();
        }
    }

    public final void onLoadMore() {
        ItemAdapter var10000 = this.adap;
        if (this.adap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adap");
        }

       // ItemAdapter.addprogress$default(var10000, 0, 1, (Object)null);
        var10000 = this.adap;
        if (this.adap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adap");
        }

       // RecyclerView var10001 = (RecyclerView) getActivity().findViewById(R.id.list1);
        RecyclerView var10001 = (RecyclerView)_$_findCachedViewById(R.id.list1);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "list1");
        LayoutManager var2 = var10001.getLayoutManager();
        if (var2 == null) {
            Intrinsics.throwNpe();
        }

        Intrinsics.checkExpressionValueIsNotNull(var2, "list1.layoutManager!!");
        var10000.notifyItemInserted(var2.getItemCount());
        RecyclerView var1 = (RecyclerView)_$_findCachedViewById(R.id.list1);
        var10001 = (RecyclerView)_$_findCachedViewById(R.id.list1);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "list1");
        var2 = var10001.getLayoutManager();
        if (var2 == null) {
            Intrinsics.throwNpe();
        }

        Intrinsics.checkExpressionValueIsNotNull(var2, "list1.layoutManager!!");
        var1.smoothScrollToPosition(var2.getItemCount());
        this.mainHandler.postDelayed((Runnable)(new Runnable() {
            public final void run() {
              //  ItemAdapter.removeprogress$default(ListActivity.this.getAdap(), 0, 1, (Object)null);
                ArrayList newdata = DashboardFragment.this.getNewData();
                DashboardFragment.this.getAdap().addAll(newdata);
            }
        }), 2000L);
    }

    public final void gsonconvert(@NotNull String jsonobj) {
        Intrinsics.checkParameterIsNotNull(jsonobj, "jsonobj");
        Gson gsonobj = new Gson();
        Bokji[] bokji = (Bokji[])gsonobj.fromJson(jsonobj, Bokji[].class);
    }

    @NotNull
    public final ArrayList makedemo() {
        boolean var2 = false;
        ArrayList arr = new ArrayList();
        int var4 = 1;

        for(byte var3 = 10; var4 <= var3; ++var4) {
            arr.add(new Bokji("1", "1", "1", "1", "1", "1", "1", "1", "1"));
        }

        return arr;
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = root.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }

    public DashboardFragment() {
        boolean var1 = false;
        ArrayList var3 = new ArrayList();
        this.items = var3;
        this.cate_mid = "영유아";
        this.cate_low = "전체";
        this.demotest = true;
        this.mainHandler = new Handler();
    }

}