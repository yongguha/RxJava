package com.ygha.interval;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MyClass {

    public static void main(String[] args){

        //CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(5);

        source.subscribe(s->System.out.println("data is :"+s));
        //source.subscribe(Log::it);
        //CommonUtils.sleep(100);


    }


}