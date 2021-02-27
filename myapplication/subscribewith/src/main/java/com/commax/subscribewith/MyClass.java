package com.commax.subscribewith;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.DisposableLambdaObserver;
import io.reactivex.observers.DisposableObserver;

public class MyClass {

    public static void main(String[] args){

        /**
         * Observable<Integer> observable = Observable.just(1);
         *         observable.subscribe(new Observer<Integer>() {
         *             @Override
         *             public void onSubscribe(Disposable d) {
         *
         *             }
         *
         *             @Override
         *             public void onNext(Integer integer) {
         *                 System.out.println("onNext : "+integer);
         *             }
         *
         *             @Override
         *             public void onError(Throwable e) {
         *
         *             }
         *
         *             @Override
         *             public void onComplete() {
         *                 System.out.println("onComplete");
         *             }
         *         });
         */

        Observable<Integer> observable = Observable.just(1);
        observable.subscribe(
                        x->System.out.println("onNext :"+ x),
                        x->System.out.println("onError"),
                        ()->System.out.println("onComplete")

                );

        Observable<String> stringObservable = Observable.just("test");
        stringObservable.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

