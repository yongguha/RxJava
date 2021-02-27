package com.commax.range;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MyClass {
    public static void main(String[] args) throws Exception{
        Flowable<Integer> flowable = Flowable.range(10, 3);

        //flowable.subscribe(new DebugSubscriber<>());
        flowable.subscribe(
                s->System.out.println("onNext :" + s),
                s->System.out.println("onError:" + s),
                ()->System.out.println("onComplete")

        );

        System.out.println("----------------------------------------------------------");

        Flowable<Long> source = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(5);

        source.subscribe(s->System.out.println("data is :"+s));
        Thread.sleep(5000L);


        System.out.println("----------------------------------------------------------");

        String[] balls ={"R", "G", "B"};
        Observable<String> repeatsource = Observable.fromArray(balls).repeat(3);
        repeatsource
                .doOnComplete(()->System.out.println("doOnComplete"))
                .subscribe(s->System.out.println("Repeat value:" + s));













    }
}