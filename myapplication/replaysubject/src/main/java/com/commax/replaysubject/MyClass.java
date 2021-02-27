package com.commax.replaysubject;

import io.reactivex.subjects.ReplaySubject;

public class MyClass {

    public static void main(String[] args){

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.subscribe(d->System.out.println("subscribe #1 : " + d));

        replaySubject.onNext("1");
        replaySubject.onNext("2");

        replaySubject.subscribe(d->System.out.println("subscribe #2 : " + d));
        replaySubject.onNext("3");

        replaySubject.onComplete();


    }


}