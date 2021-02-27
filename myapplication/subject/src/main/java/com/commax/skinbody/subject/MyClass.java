package com.commax.skinbody.subject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class MyClass {
    public static void main(String[] args) {

        PublishSubject<Integer> subject = PublishSubject.<Integer>create();
        subject
                .filter(data->(data%2)!=0)
                .flatMap(value-> Observable.range(0, 10).map(index->value))
                .subscribe(values->System.out.println(values));
        subject.onNext(0);
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
        subject.onNext(5);

        System.out.println("==========================================================");


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("onNext = " + integer);
            }
        });


        System.out.println("==========================================================");


        // ignore data which is published befor subscribe
        PublishSubject<Integer> subject2 = PublishSubject.create();
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("onNext = " + integer);
            }
        });

        for (int i = 0; i < 10; i++) {
            subject2.onNext(i);
        }


        PublishSubject<Integer> subjectSample = PublishSubject.create();
        subjectSample.subscribe(data->System.out.println("data is :" + data));

        subjectSample.onNext(1);
        subjectSample.onNext(2);
        subjectSample.onNext(3);;

    }





}