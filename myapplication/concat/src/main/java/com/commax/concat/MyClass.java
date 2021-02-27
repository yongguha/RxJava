package com.commax.concat;

import com.ygha.common.CommonUtils;
import com.ygha.common.Log;
import com.ygha.common.OkHttpHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.ygha.common.CommonUtils.API_KEY;

public class MyClass {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";

    public void run() {
        CommonUtils.exampleStart();

        Observable<String> source = Observable.just(URL + API_KEY)
                .map(OkHttpHelper::getWithLog)
                .subscribeOn(Schedulers.io())
                .share()
                .observeOn(Schedulers.newThread());

        source.map(this::parseTemperature).subscribe(Log::it);
        source.map(this::parseCityName).subscribe(Log::it);
        source.map(this::parseCountry).subscribe(Log::it);

        CommonUtils.sleep(3000);
    }

    private String parseTemperature(String json) {
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }

    private String parseCityName(String json) {
        return parse(json, "\"name\":\"[a-zA-Z]*\"");
    }

    private String parseCountry(String json) {
        return parse(json, "\"country\":\"[a-zA-Z]*\"");
    }

    private String parse(String json, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(json);
        if (match.find()) {
            return match.group();
        }
        return "N/A";
    }



    public static void main(String[] args){

        System.out.println("concatMap");
        String[] data1 = {"1","2","3"};
        String[] data2 = {"one","two", "three"};

        Observable<String> source1 = Observable.fromArray(data1)
                .doOnComplete(()->System.out.println("doOnComplete_1"));

        Observable<String> source2 = Observable.fromArray(data2)
                .doOnComplete(()->System.out.println("doOnComplete_2"));

        Observable<String> source = Observable.concat(source1, source2)
                .doOnComplete(()->System.out.println("concat : doOnComplete"));
        source.subscribe(s->System.out.println("subscriber : "+s));

        System.out.println("=============================================================");


        MyClass myClass = new MyClass();
        myClass.run();




    }
}