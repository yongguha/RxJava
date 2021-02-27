package com.ygha.behaviorsubject;

import io.reactivex.subjects.BehaviorSubject;

public class MyClass {

    public static void main(String[] args){

        BehaviorSubject<String> subject = BehaviorSubject.createDefault("PINK");

        //구독하기 전 Pink 받고, 그 이후는 순서대로
        subject.subscribe(data -> System.out.println("Subscriber #1 =>" + data));
        subject.onNext("RED");
        subject.onNext("PURPLE");
        subject.onNext("GREEN");
        //구독하기 전 마지막 green 받고 , 다음 순서대로 blue
        subject.subscribe(data -> System.out.println("Subscriber #2 =>" + data));
        subject.onNext("BLUE");
        subject.onComplete();





        System.out.println("========================================================");
        BehaviorSubject<String> subject1 = BehaviorSubject.createDefault("x");
        subject1.subscribe(data -> System.out.println("Subscriber #1 =>" + data));

        subject1.onNext("Alpha");
        subject1.onNext("Beta");
        subject1.onNext("Gamma");

        subject1.subscribe(data -> System.out.println("Subscriber #2 =>" + data));
        subject1.subscribe(data -> System.out.println("Subscriber #3 =>" + data));







    }

}