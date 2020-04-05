package com.example.myapplication

import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class ServAsync : AsyncTask<String, Void, ArrayList<ArrayList<String>>>() {
    lateinit var fileurl:URL
    // param으로 나중에 받아오기

    override fun onPostExecute(result: ArrayList<ArrayList<String>>?) {
        super.onPostExecute(result)
        //startActivity(intent)
        //return result
    }

    override fun doInBackground(vararg params: String?): ArrayList<ArrayList<String>> {
        Log.d("doinback",params[0])
        return listparse(params[0]!!)
    }

    fun listparse(cate_mid: String): ArrayList<ArrayList<String>> {
        val parserCreator = XmlPullParserFactory.newInstance()
        val parser = parserCreator.newPullParser()

        Log.d("lists", cate_mid)
        var servid=false
        var servDgst=false
        var servNm =false



        when(cate_mid){
            "영유아" -> fileurl = URL("http://52.79.72.52:5000/getbabyxml")
            "노인" -> fileurl = URL("http://52.79.72.52:5000/getnoxml")
            "중장년" -> fileurl = URL("http://52.79.72.52:5000/getjungxml")
            "임산부" -> fileurl = URL("http://52.79.72.52:5000/getpregxml")
            "아동청소년" -> fileurl = URL("http://52.79.72.52:5000/getkidxml")
            "청년" -> fileurl = URL("http://52.79.72.52:5000/getchungxml")
        }
        Log.d("lists", fileurl.toString())
        //url잘가져옴
        parser.setInput(fileurl.openStream(), null)

        var parserEvent = parser.getEventType()
        var idlist:ArrayList<String> = ArrayList()
        var namelist:ArrayList<String> = ArrayList()
        var detaillist:ArrayList<String> = ArrayList()


        while (parserEvent != XmlPullParser.END_DOCUMENT) {
            when(parserEvent) {
                XmlPullParser.START_TAG -> {//parser가 시작 태그를 만나면 실행
                    Log.d("parser1",parser.name)
                    if (parser.name.equals("servId")) {
                        servid=true
                    }
                    if (parser.name.equals("servDgst")) {
                        servDgst=true
                    }
                    if (parser.name.equals("servNm")) {
                        servNm=true
                    }
                }
                XmlPullParser.TEXT ->{
                    if(servid) {
                        Log.d("parser",parser.text)
                        idlist.add(parser.text)
                        servid=false
                    }
                    if(servDgst) {
                        Log.d("parser",parser.text)
                        detaillist.add(parser.text)
                        servDgst=false
                    }
                    if(servNm) {
                        Log.d("parser",parser.text)
                        namelist.add(parser.text)
                        servNm=false
                    }
                }
            }
            parserEvent = parser.next()
        }
        var arrs = arrayListOf(idlist,namelist,detaillist)

        return arrs
    }
}