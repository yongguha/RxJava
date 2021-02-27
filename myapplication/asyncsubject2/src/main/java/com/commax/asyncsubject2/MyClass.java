package com.commax.asyncsubject2;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class MyClass {



    public static void main(String[] args){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data->System.out.println("subscriber#1 ==>" + data));

        subject.onNext("RED");
        subject.onNext("BLUE");

        subject.subscribe(data->System.out.println("subscriber#2 ==>" + data));

        subject.onNext("GREEN");
        subject.onComplete();
        subject.subscribe(data->System.out.println("subscriber#3 ==>" + data));


        System.out.println("========================================================");


        Float[] temperature = {10.1f, 13.4f, 12.f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject1 = AsyncSubject.create();
        subject1.subscribe(data->System.out.println("subscriber #4 =>" + data));

        source.subscribe(subject1);




    }
}