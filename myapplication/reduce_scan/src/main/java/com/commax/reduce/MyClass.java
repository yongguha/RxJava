package com.commax.reduce;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class MyClass {
    public static void main(String[] args){

        System.out.println("<reduce>");

        Single<Integer> single = Flowable.just(1, 10, 100, 1000, 10000)
                .reduce(0, (x,y)->x+y);

        single.subscribe(System.out::println);

        System.out.println("====================================================");
        System.out.println("<scan>");

        Flowable<Integer> flowable = Flowable.just(1, 10, 100, 1000, 10000)
                .scan(0, (x,y)->x+y);

        flowable.subscribe(System.out::println);





    }
}