package com.ygha.connectableobservable;

import com.ygha.common.CommonUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;

public class MyClass {


    public static void main(String[] args){



        String[] dt = {"1", "3", "5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i->dt[i])
                .take(dt.length);

        ConnectableObservable<String> source = balls.publish();
        source.subscribe(data-> System.out.println("Subscriber #1 ==>" + data));
        source.subscribe(data-> System.out.println("Subscriber #2 ==>" + data));

        /*
        subscribe해도 데이터 발행하지 않고, connect()를 호출해야 데이터를 발행한다.
        connect()를 호출하면, 그때 까지 구독했던 모든 구독자에게 데이터를 발행한다.
        connect()이후, 구독한 구독자에게는 구독 이후에 발행한 데이터만 발행한다.

         */

        source.connect();
        CommonUtils.sleep(250);
        source.subscribe(data-> System.out.println("Subscriber #3 ==>" + data));
        CommonUtils.sleep(100);


        System.out.println("==============================================================");


        PublishSubject<String> publishSubject = PublishSubject.create();
        ConnectableObservable<String> connectableObservable = publishSubject.publish();

        publishSubject.onNext("1");;
        connectableObservable.subscribe(d->System.out.println("subscribe #1 :" + d));
        connectableObservable.subscribe(d->System.out.println("subscribe #2 :" + d));
        connectableObservable.connect();

        //subscribe해도 데이터 발행하지 않고, connect()를 호출해야 데이터를 발행한다.
        //connect()를 호출하면, 그때 까지 구독했던 모든 구독자에게 connect 이후 발행된 데이터를 발행한다.
        //connect()이후, 구독한 구독자에게는 구독 이후에 발행한 데이터만 발행한다.
        //데이터 1은 connect 이전의 데이터 이므로 받지 못한다.
        publishSubject.onNext("2");
        publishSubject.onNext("3");

        connectableObservable.subscribe(d->System.out.println("subscribe #3 :" + d));
        publishSubject.onNext("4");

        publishSubject.onComplete();


    }

}