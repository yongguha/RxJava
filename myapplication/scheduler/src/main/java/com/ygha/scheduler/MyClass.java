package com.ygha.scheduler;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class MyClass {
    public static void main(String[] args) throws Exception{

        Flowable.just(1,2,3,4)
                .subscribeOn(Schedulers.computation())
                .subscribe(data-> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName+":"+data);
                });

        Thread.sleep(500);

    }
}