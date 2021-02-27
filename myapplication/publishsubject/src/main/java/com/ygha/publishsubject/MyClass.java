package com.ygha.publishsubject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MyClass {

    public static void main(String[] args){


        System.out.println("============================================================================");

        Observable.just("RED", "GREEN", "BLUE")
                .doOnNext(s->System.out.println("doOnNext:" +s))
                .subscribe(
                        v->System.out.println("onNext() :value :" + v),
                        err->System.out.println("onError :err :" + err.getMessage()),
                        ()->System.out.println("onComplete")

                );

        System.out.println("============================================================================");

        PublishSubject<String> sourceSubject = PublishSubject.<String>create();
        Observable<String> source2 = sourceSubject
                                .doOnNext(item->System.out.println("doOnNext :"+item));

        source2.subscribe(
                s->System.out.println("Subscriber :" + s),
                s->System.out.println("onError"),
                ()->System.out.println("onComplete")
        );

        sourceSubject.onNext("ONE");

        System.out.println("============================================================================");

        PublishSubject<String> publishSubject = PublishSubject.<String>create();
        publishSubject
                .doOnNext( item ->  System.out.println("Do something with the item " + item) )
                .subscribe(s->System.out.println(s));

        publishSubject.onNext("TEST");

        System.out.println("============================================================================");

        PublishSubject<String> publishSubject2 = PublishSubject.<String>create();
        publishSubject2
                //.doOnNext( item ->  System.out.println("Do something with the item " + item) )
                .doOnEach( s->{
                        if(s.isOnNext())
                            System.out.println("OnNext:"+s);
                        if(s.isOnComplete())
                            System.out.println("OnComplete :"+s);
                        if(s.isOnError())
                            System.out.println("OnError");
                        }
                )
                .subscribe(s->System.out.println("subscriber:"+s));

        publishSubject2.onNext("Lambda Express");
        publishSubject2.onComplete();
        System.out.println("============================================================================");
        /*
        Observable<String> source = publishSubject
                .doOnNext( item ->  System.out.println("Do something with the item " + item) );
        source.subscribe(System.out::println, Throwable::printStackTrace);
        publishSubject.onNext("1");
         */


    }


}