package com.commax.buffer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class MyClass {
    public static void main(String[] args) throws Exception{

        Flowable<List<Long>> flowable =
                Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(10)
                .buffer(3);
        //flowable.subscribe(new DebugSubscriber<>());
        flowable.subscribe(s->System.out.println(s));
        Thread.sleep(1100);

        System.out.println("===========================================");

        Flowable<List<Long>> listFlowable =
                Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(7)
                .buffer(
                        ()-> Flowable.timer(1000L, TimeUnit.MILLISECONDS));
        listFlowable.subscribe(s->System.out.println(s));
        Thread.sleep(4000L);



    }
}