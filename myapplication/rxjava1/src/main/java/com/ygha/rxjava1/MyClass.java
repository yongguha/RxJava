package com.ygha.rxjava1;

import com.sun.media.jfxmediaimpl.MediaDisposer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyClass {

    public static void main(String[] args){

        Observable.just("RED", "GREEN", "BLUE")
                .doOnNext(s->System.out.println("doOnNext:" +s))
                .subscribe(
                        v->System.out.println("onNext() :value :" + v),
                        err->System.out.println("onError :err :" + err.getMessage()),
                        ()->System.out.println("onComplete")

                );




        Observable<String> source = Observable.just("RED", "GREEN", "BLUE");

        Disposable d = source.subscribe(
                v->System.out.println("onNext() :value :" + v),
                err->System.out.println("onError :err :" + err.getMessage()),
                ()->System.out.println("onComplete")
        );

        source.subscribe(new Observer<String>() {
                             @Override
                             public void onSubscribe(Disposable d) {

                             }

                             @Override
                             public void onNext(String s) {

                             }

                             @Override
                             public void onError(Throwable e) {

                             }

                             @Override
                             public void onComplete() {

                             }
                         });



                System.out.println("isDisposed():" + d.isDisposed());





    }



}