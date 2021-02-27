package com.commax.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MyClass {
    public static void main(String[] args){

        String[] data={"1", "7","2", "3","6"};
        CommonUtils.exampleStart();
        Observable<String> earlySource = Observable.fromArray(data)
                .take(4)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b)->a);
        Observable<String> lastSource = Observable.just(data[4])
                .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b)->a);
        Observable<String> source = Observable.concat(earlySource,lastSource)
                .sample(300L, TimeUnit.MILLISECONDS);
        source.subscribe(s->System.out.println("subsriber :" + s));
        CommonUtils.sleep(1000);


    }
}