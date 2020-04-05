package com.example.myapplication;

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.id.list1
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class ListActivity : AppCompatActivity() {
    var items = arrayListOf<ServiceModel>()
    var cate_mid="영유아"
    var cate_low="전체"

    var demotest = false
    lateinit var adap: ItemAdapter
    var mainHandler = Handler()
    var servList= ArrayList<ServiceModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        adap = ItemAdapter(this, items)
        list2.adapter = adap
        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list2.layoutManager = layoutManager

        bt_show.setOnClickListener {
            //var bokjilist = BokjiRetrofit(cate_mid, cate_low).response

          //  bokjilist.enqueue()
            //리스폰스 어떻게 받아오지??enqueue,,,,공부를 좀더,,
           // items = response.body()!!.documents
            var lists = ServAsync().execute(cate_mid).get()//목록에서 id값 추출

            var idlist = lists.get(0)
            var namelist = lists.get(1)
            var detaillist = lists.get(2)

            for(i in 1..idlist.size){
                Log.d("idlist", idlist.get(i-1))
                servList.add(ServiceModel(idlist.get(i-1),namelist.get(i-1),"",detaillist.get(i-1),"","","",""))
            }
            items=servList

            //adap.replaceAll(getNewData())
            var random = Random()
            fun Random.nextInt(range: IntRange): Int { //범위지정할려고 추가
                return range.start + nextInt(range.last - range.start)
            }
            random.nextInt(300000..2000000)
            animateTextView(0,100000,tv_money)
            adap.replaceAll(getNewData())

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
}
