package com.commax.publishsubject2;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MyClass {

    public static void main(String[] args){

        PublishSubject<Integer> subject = PublishSubject.<Integer>create();

        subject.doOnNext(s->System.out.println("doOnnext:" + s))
                .filter(data->(data%2)!=0)
                .doOnComplete(()->System.out.println("doOnComplete"))
                .subscribe(values->System.out.print(values));

        subject.onNext(100);
        subject.onNext(200);
        subject.onNext(300);
        //subject.onComplete();




    }
}
