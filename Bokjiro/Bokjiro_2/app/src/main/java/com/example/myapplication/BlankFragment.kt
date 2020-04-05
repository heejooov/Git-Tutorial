package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_blank.*
import java.util.*
import kotlin.collections.ArrayList


class BlankFragment : Fragment() {


    lateinit var name :String
    lateinit var cate_mid:String

    var items = arrayListOf<ServiceModel>()

    var demotest = false
    lateinit var adap: ItemAdapter
    var servList= ArrayList<ServiceModel>()
    fun Random.nextInt(range: IntRange): Int { //범위지정할려고 추가
        return range.start + nextInt(range.last - range.start)
    }
    var random = Random()
    var rannum = random.nextInt(3000..20000)*100

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var pref = context!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        name = pref.getString("name","박채은")!!
        var baby = pref.getInt("baby",1)
        var kid = pref.getInt("kid",0)
        var chung = pref.getInt("chung",0)
        var jung = pref.getInt("jung",0)
        var no = pref.getInt("no",0)
        var freg = pref.getInt("freg",0)
        // Inflate the layout for this fragment
        if(baby == 1)
            cate_mid="영유아"
        else if(kid==1)
            cate_mid="아동청소년"
        else if(chung==1)
            cate_mid="청년"
        else if(jung==1)
            cate_mid="중장년"
        else if(no==1)
            cate_mid="노인"
        else cate_mid="임산부"
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onStart() {
        super.onStart()
//
//        adap = ItemAdapter(activity as MainActivity, items)
//        list1.adapter = adap
//        var layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        list1.layoutManager = layoutManager
//
//        bt_show.setOnClickListener {
//            //var bokjilist = BokjiRetrofit(cate_mid, cate_low).response
//
//            //  bokjilist.enqueue()
//            //리스폰스 어떻게 받아오지??enqueue,,,,공부를 좀더,,
//            // items = response.body()!!.documents
//            var idlist = ServAsync().execute(cate_mid).get()//목록에서 id값 추출
//
//
//            for(i in 1..idlist.size){
//                Log.d("idlist", idlist.get(i-1))
//                servList.add(getServDetail(idlist.get(i-1)))
//            }
//            items=servList
//
//            //adap.replaceAll(getNewData())
//
//
//            animateTextView(0,rannum,tv_money)
//            adap.replaceAll(getNewData())
//
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("catemid", cate_mid)
        view.apply {
            tv_show.setText(name+"님이 받을 혜택은 다음과 같습니다.")
            adap = ItemAdapter(activity as MainActivity, items)
            list1.adapter = adap
            var layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            list1.layoutManager = layoutManager

            bt_show.setOnClickListener {

                var lists = ServAsync().execute(cate_mid).get()//목록에서 id값 추출

                var idlist = lists.get(0)
                var namelist = lists.get(1)
                var detaillist = lists.get(2)


                for(i in 1..idlist.size){
                    Log.d("idlist", idlist.get(i-1))
                    servList.add(ServiceModel(idlist.get(i-1),namelist.get(i-1),"",detaillist.get(i-1),"","","",""))
                }
                items=servList

                animateTextView(0,rannum,tv_money)
                adap.replaceAll(getNewData())

            }
        }
    }

    fun getServDetail(srvid:String) : ServiceModel{
        var apiapi = changeCode()
        var detailserv = apiapi.callDetailServ(srvid)
        return detailserv
    }
    fun animateTextView(initialValue: Int, finalValue: Int, textview: TextView) {

        val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
        valueAnimator.duration = 1500

        valueAnimator.addUpdateListener { valueAnimator ->
            textview.text = valueAnimator.animatedValue.toString()
        }
        valueAnimator.start()

    }

    fun getNewData(): ArrayList<ServiceModel> {//TODO 데이터 클릭이벤트에서 옮기기
        Log.d("getNewData()","getNewData()")
        if (demotest){//걍데모값 넣을때
            return makedemo()
        }
        else if (items.size != 0) {
            return items
        }
        else
            return (arrayListOf<ServiceModel>())

    }

    fun makedemo(): ArrayList<ServiceModel> {
        var arr= arrayListOf<ServiceModel>()
        for(i in 1..10) {
            arr.add(
                    ServiceModel("1", "1", "1", "1", "1", "1", "1", "1")
            )
        }
        return arr
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BlankFragment()
    }
}
