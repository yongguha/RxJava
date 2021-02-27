package com.commax.filter;


import io.reactivex.Observable;
import io.reactivex.Single;

public class MyClass {

    public static void main(String[] args){
        String[] objs = {"Circle", "Diamond", "Triangle", "Diamond_2", "Circle_2", "Triangle_2"};

        Observable observable = Observable.fromArray(objs)
                .filter(obj->obj.contains("Circle"));

        observable.subscribe(System.out::println);

        System.out.println("==========================================================================");


        Integer[] numbers = {100, 200, 300, 400, 500, 600};
        Single<Integer> single;
        Observable<Integer> observable1;

        single = Observable.fromArray(numbers).first(-1);
        single.subscribe(s->System.out.println("first:" + s));





    }

}