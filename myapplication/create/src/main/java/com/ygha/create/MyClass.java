package com.ygha.create;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;

public class MyClass {
    public static void main(String[] args){

        System.out.println("===========================================================");
        /*
        Observable.create(
                emitter -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();

                }).subscribe(
                        v->System.out.println("onNext() :value :" + v),
                err->System.out.println("onError :err :" + err.getMessage()),
                ()->System.out.println("onComplete"));

                Observable<Integer> source =
                Observable.create(
                        emitter -> {
                            emitter.onNext(100);
                            emitter.onNext(200);
                            emitter.onNext(300);
                            emitter.onComplete();

                        });

        source.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer data) throws Exception {
                System.out.println("result " + data);
            }
        });
         */

        Observable<Integer> source = Observable.create(
                emitter -> {
                    emitter.onNext(100);
                    emitter.onComplete();
                    emitter.onNext(200);
                    emitter.onNext(300);
                }

        );

        source.subscribe(data->System.out.println("result :" + data));

        System.out.println("===========================================================");

        Observable<Integer> source2 = Observable.just(0)
                .doOnSubscribe((i)->System.out.println("doOnSubscribe"));

        source2.subscribe(i->System.out.println("data :" + i));

        System.out.println("===========================================================");

        Observable<Object> source3 =
                Observable.create(
                        e -> {
                            e.onNext(100);
                            e.onNext(200);
                            e.onNext(300);
                        }).doOnNext(i->System.out.println("doOnNext :"+i));

        source3.subscribe(i->System.out.println("data =" + i));

        System.out.println("===========================================================");
    }
}