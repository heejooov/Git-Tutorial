package com.example.myapplication;

import android.os.StrictMode;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class changeCode {

    //생애주기 lifeArray
    String baby="&lifeArray=001",child="&lifeArray=002",stud="&lifeArray=003"
            ,adult="&lifeArray=004",mid="&lifeArray=005",old="&lifeArray=006";

    //가구유형 trgterIndvdlArray
    String one="&trgterIndvdlArray=002",multi="&trgterIndvdlArray=003";
    //charTrgterArray
    String disa="&charTrgter=004",preg="&charTrgterArray=003",nat="&charTrgterArray=005",
            find="&charTrgterArray=006";
    String safe="&desireArray=0000000",health="&desireArray=1000000";



    String change(String in){
        if(in.equals("영유아"))return baby;
        else if(in.equals("아동"))return child;
        else if(in.equals("청소년"))return stud;
        else if(in.equals("청년"))return adult;
        else if(in.equals("중장년"))return mid;
        else if(in.equals("노년"))return old;
        else if(in.equals("한부모"))return one;
        else if(in.equals("다문화"))return multi;
        else if(in.equals("장애인"))return disa;
        else if(in.equals("국가유공자"))return nat;
        else if(in.equals("임신"))return preg;
        else if(in.equals("안전"))return safe;
        else if(in.equals("건강")) return health;
        return "&searchWrd="+in;
    }

    String KEY="WU3mzjeOk9dyTh0TGhzyPJgVTF896mvmogKUt4Pxmnt87Xuixv2VhA3Sfa52ZGNVSh0rhtUjiwLspxg%2Fm4hYvQ%3D%3D";

    boolean servList = false, inJurMnofNm = false, inJurOrgNm = false, inServDgst = false, inServDtlLink = false;
    boolean inServId = false, inServNm = false, inSvcfrstRegTs = false;
    boolean inTgtrDtlCn=false, inServSeDetailNm=false,inServSeDetailLink=false;
    boolean in2servId,in2servNm,in2jurMnofNm,in2servDgst,in2tgtrDtlCn,in2slctCritCn,in2alwServCn,in2servSeCode =false;

//    Service service;

    String jurMnofNm = null, jurOrgNm = null, servDgst = null, servDtlLink = null;
    String servId = null, servNm=null, svcfrstRegTs=null, alwServCn=null;
    String tgtrDtlCn=null,servSeDetailNm=null,servSeDetailLink=null;
    String servId2, servNm2, jurMnofNm2, servDgst2, tgtrDtlCn2, slctCritCn2, alwServCn2, servSeCode2 = "0";

    String[][] callApi(String str) {

        String[][] outS=new String[20][3];
        StrictMode.enableDefaults();
        String ss=change(str);

        int i=0;
        Log.d("changegegege",ss);
        try {
            URL url = new URL("http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + KEY +
                    "&callTp=L&pageNum=1&numOfRows=20"+ss); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("jurMnofNm")) { //title 만나면 내용을 받을수 있게 하자
                            inJurMnofNm = true;
                        }
                        if (parser.getName().equals("jurOrgNm")) { //address 만나면 내용을 받을수 있게 하자
                            inJurOrgNm = true;
                        }
                        if (parser.getName().equals("servDgst")) { //mapx 만나면 내용을 받을수 있게 하자
                            inServDgst = true;
                        }
                        if (parser.getName().equals("servDtlLink")) { //mapy 만나면 내용을 받을수 있게 하자
                            inServDtlLink = true;
                        }
                        if (parser.getName().equals("servId")) { //mapy 만나면 내용을 받을수 있게 하자
                            inServId = true;
                        }
                        if (parser.getName().equals("servNm")) { //mapy 만나면 내용을 받을수 있게 하자
                            inServNm = true;
                        }
                        if (parser.getName().equals("svcfrstRegTs")) { //mapy 만나면 내용을 받을수 있게 하자
                            inSvcfrstRegTs = true;
                        }

                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
//                            tv.setText(tv.getText() + "에러");
                            Log.d("ERROR", "ERRORRRR");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;


                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inJurMnofNm) {
                            jurMnofNm=parser.getText();
                            inJurMnofNm = false;
                        }
                        if (inJurOrgNm) { //isAddress이 true일 때 태그의 내용을 저장.
                            jurOrgNm = parser.getText();
                            inJurOrgNm = false;
                        }
                        if (inServDgst) { //isMapx이 true일 때 태그의 내용을 저장.
                            servDgst = parser.getText();
                            inServDgst = false;
                        }
                        if (inServDtlLink) { //isMapy이 true일 때 태그의 내용을 저장.
                            servDtlLink = parser.getText();
                            inServDtlLink = false;
                        }
                        if (inServId) { //isMapy이 true일 때 태그의 내용을 저장.
                            servId = parser.getText();
                            inServId = false;
                        }
                        if (inServNm) { //isMapy이 true일 때 태그의 내용을 저장.
                            servNm = parser.getText();
                            inServNm = false;
                        }
                        if (inSvcfrstRegTs) { //isMapy이 true일 때 태그의 내용을 저장.
                            svcfrstRegTs = parser.getText();
                            inSvcfrstRegTs = false;
                        }

                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("servList")) {
//                            tv.setText(tv.getText()+"소관부처명 : "+ jurMnofNm +"\n 소관조직명: "+ jurOrgNm +"\n 서비스 요약 : " + servDgst
//                                    +"\n 서비스상세링크 : " + servDtlLink +  "\n 서비스id : " + servId+ "\n 서비스명 : " + servNm
//                                    +"\n 서비스등록일 : " +svcfrstRegTs  +"\n");
//                                Log.d("changegegege","소관부처명 : "+ jurMnofNm +"\n 소관조직명: "+ jurOrgNm +"\n 서비스 요약 : " + servDgst
//                                    +"\n 서비스상세링크 : " + servDtlLink +  "\n 서비스id : " + servId+ "\n 서비스명 : " + servNm
//                                    +"\n 서비스등록일 : " +svcfrstRegTs  +"\n");
//                            Service service=new Service(jurMnofNm,jurOrgNm,servDgst,servDtlLink,servId,servNm,svcfrstRegTs);
                            servList = false;

                            outS[i][0]=servNm;
                            outS[i][1]=servDgst;
                            outS[i][2]=servId;

                            i++;
                        }
                        break;
                }

                parserEvent = parser.next();

            }
        } catch (Exception e) {
            //tv.setText("에러가..났습니다...");

        } return outS;
    }



    String[] callApi2(String str) {

        String[] outS=new String[4];
        StrictMode.enableDefaults();
        String ss=str;
        ServiceModel sm;
        int i=0;
        Log.d("changegegege",ss);
        try {
            URL url = new URL("http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + KEY +
                    "&callTp=D&servId="+ss); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("servNm")) { //title 만나면 내용을 받을수 있게 하자
                            inServNm = true;
                            in2servNm=true;
                        }
                        if (parser.getName().equals("tgtrDtlCn")) { //address 만나면 내용을 받을수 있게 하자
                            inTgtrDtlCn = true;
                            in2tgtrDtlCn = true;
                        }
                        if (parser.getName().equals("servSeDetailNm")) { //mapx 만나면 내용을 받을수 있게 하자
                            inServSeDetailNm = true;
                        }
                        if (parser.getName().equals("servSeDetailLink")) { //mapy 만나면 내용을 받을수 있게 하자
                            inServSeDetailLink = true;
                        }
                        if (parser.getName().equals("servId")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2servId = true;
                        }
                        if (parser.getName().equals("jurMnofNm")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2jurMnofNm = true;
                        }
                        if (parser.getName().equals("servDgst")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2servDgst = true;
                        }
                        if (parser.getName().equals("slctCritCn")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2slctCritCn = true;
                        }
                        if (parser.getName().equals("alwServCn")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2alwServCn = true;
                        }
                        if (parser.getName().equals("servSeCode")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2servSeCode = true;
                        }



                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
//                            tv.setText(tv.getText() + "에러");
                            Log.d("ERROR", "ERRORRRR");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;


                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inServNm||in2servNm) {
                            servNm=parser.getText();
                            outS[0]=servNm;
                            servNm2=parser.getText();
                            in2servNm = false;
                            inServNm = false;
                        }
                        if (inTgtrDtlCn||in2tgtrDtlCn) { //isAddress이 true일 때 태그의 내용을 저장.
                            tgtrDtlCn = parser.getText();
                            outS[1]=tgtrDtlCn;
                            tgtrDtlCn2 =parser.getText();
                            in2tgtrDtlCn =false;
                            inTgtrDtlCn = false;
                        }
                        if (inServSeDetailNm) { //isMapx이 true일 때 태그의 내용을 저장.
                            servSeDetailNm = parser.getText();
                            outS[2]=servSeDetailNm;
                            inServSeDetailNm = false;
                        }
                        if (inServSeDetailLink) { //isMapy이 true일 때 태그의 내용을 저장.
                            servSeDetailLink = parser.getText();
                            outS[3]=servSeDetailLink;
                            inServSeDetailLink = false;
                        }
                        if (in2servId) { //isMapy이 true일 때 태그의 내용을 저장.
                            servId2 = parser.getText();
                            in2servId = false;
                        }
                        if (in2jurMnofNm) { //isMapy이 true일 때 태그의 내용을 저장.
                            jurMnofNm2 = parser.getText();
                            in2jurMnofNm = false;
                        }if (in2servDgst) { //isMapy이 true일 때 태그의 내용을 저장.
                        servDgst2 = parser.getText();
                        in2servDgst = false;
                    }if (in2slctCritCn) { //isMapy이 true일 때 태그의 내용을 저장.
                        slctCritCn2 = parser.getText();
                        in2slctCritCn = false;
                    }if (in2alwServCn) { //isMapy이 true일 때 태그의 내용을 저장.
                        alwServCn2 = parser.getText();
                        in2alwServCn = false;
                    }if (in2servSeCode) { //isMapy이 true일 때 태그의 내용을 저장.
                        servSeCode2 = parser.getText();
                        in2servSeCode = false;
                    }


                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("servList")) {
//                            tv.setText(tv.getText()+"소관부처명 : "+ jurMnofNm +"\n 소관조직명: "+ jurOrgNm +"\n 서비스 요약 : " + servDgst
//                                    +"\n 서비스상세링크 : " + servDtlLink +  "\n 서비스id : " + servId+ "\n 서비스명 : " + servNm
//                                    +"\n 서비스등록일 : " +svcfrstRegTs  +"\n");
                            Log.d("changegegege","서비스명 : "+ servNm +"\n 대상자 상세내용: "+ tgtrDtlCn +
                                    "\n 서비스 이용 및 상세 방법 : " + servSeDetailNm
                                    +"\n 서비스상세링크 : " + servSeDetailLink +"\n");
//                            Service service=new Service(jurMnofNm,jurOrgNm,servDgst,servDtlLink,servId,servNm,svcfrstRegTs);
                            servList = false;

//                            outS[0]=servNm;//서비스명
//                            outS[1]=tgtrDtlCn;//대상자 상세내용
//                            outS[2]=servSeDetailNm;//서비스 이용 및 상세 방법
//                            outS[3]=servSeDetailLink;//링크

                        }
                        break;
                }


                parserEvent = parser.next();

            }
        } catch (Exception e) {
            //tv.setText("에러가..났습니다...");

        }

        sm = new ServiceModel(servId2,servNm2, jurMnofNm2, servDgst2, tgtrDtlCn2, slctCritCn2, alwServCn2, servSeCode2);

        return outS;
    }
    ServiceModel callDetailServ(String str) {

        StrictMode.enableDefaults();
        String ss=str;
        ServiceModel sm;

        try {
            URL url = new URL("http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + KEY +
                    "&callTp=D&servId="+ss); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("servNm")) { //title 만나면 내용을 받을수 있게 하자
                            inServNm = true;
                            in2servNm=true;
                        }
                        if (parser.getName().equals("tgtrDtlCn")) { //address 만나면 내용을 받을수 있게 하자
                            inTgtrDtlCn = true;
                            in2tgtrDtlCn = true;
                        }
                        if (parser.getName().equals("servSeDetailNm")) { //mapx 만나면 내용을 받을수 있게 하자
                            inServSeDetailNm = true;
                        }
                        if (parser.getName().equals("servSeDetailLink")) { //mapy 만나면 내용을 받을수 있게 하자
                            inServSeDetailLink = true;
                        }
                        if (parser.getName().equals("servId")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2servId = true;
                        }
                        if (parser.getName().equals("jurMnofNm")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2jurMnofNm = true;
                        }
                        if (parser.getName().equals("servDgst")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2servDgst = true;
                        }
                        if (parser.getName().equals("slctCritCn")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2slctCritCn = true;
                        }
                        if (parser.getName().equals("alwServCn")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2alwServCn = true;
                        }
                        if (parser.getName().equals("servSeCode")) { //mapy 만나면 내용을 받을수 있게 하자
                            in2servSeCode = true;
                        }



                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
//                            tv.setText(tv.getText() + "에러");
                            Log.d("ERROR", "ERRORRRR");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;


                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (in2servNm) {
                            servNm2=parser.getText();
                            if(servNm2==null)
                                servNm2="0";
                            in2servNm = false;
                        }if (in2tgtrDtlCn) {
                            tgtrDtlCn2 =parser.getText();
                            if(tgtrDtlCn2==null)
                                tgtrDtlCn2="0";
                            in2tgtrDtlCn =false;
                        }if (in2servId) { //isMapy이 true일 때 태그의 내용을 저장.
                            servId2 = parser.getText();
                            if(servId2==null) servId2="0";
                            in2servId = false;
                        }if (in2jurMnofNm) { //isMapy이 true일 때 태그의 내용을 저장.
                            jurMnofNm2 = parser.getText();
                            if(jurMnofNm2==null) jurMnofNm2="0";
                            in2jurMnofNm = false;
                        }if (in2servDgst) { //isMapy이 true일 때 태그의 내용을 저장.
                            servDgst2 = parser.getText();
                            if(servDgst2==null) servDgst2="0";
                            in2servDgst = false;
                        }if (in2slctCritCn) { //isMapy이 true일 때 태그의 내용을 저장.
                            slctCritCn2 = parser.getText();
                            if(slctCritCn2==null) slctCritCn2="0";
                            in2slctCritCn = false;
                        }if (in2alwServCn) { //isMapy이 true일 때 태그의 내용을 저장.
                            alwServCn2 = parser.getText();
                            if(alwServCn2==null) alwServCn2="0";
                            in2alwServCn = false;
                        }if (in2servSeCode) { //isMapy이 true일 때 태그의 내용을 저장.
                            servSeCode2 = parser.getText();
                            if(servSeCode2==null) servSeCode2="0";
                            in2servSeCode = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("servList")) {
                            servList = false;
                        }
                        break;
                }
                parserEvent = parser.next();

            }
        } catch (Exception e) {
            //tv.setText("에러가..났습니다...");

        }

        sm = new ServiceModel(servId2,servNm2, jurMnofNm2, servDgst2, tgtrDtlCn2, slctCritCn2, alwServCn2, servSeCode2);

        return sm;
    }

}