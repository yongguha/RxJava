package com.commax.map;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MyClass {

    public static void main(String[] args){
        /*
        Observable<Integer> observable = Observable.just(1,2,3,4)
                .map(number->number*number);
        //observable.subscribe(s->System.out.println());
        observable.subscribe(System.out::println);
        */

        System.out.println("=====================================");

        Function<String, Observable<String>> getDiamond = x->
                Observable.just(x+"<>");

        String[] numbers = {"1", "2", "3", "4"};
        Observable<String> observable = Observable.fromArray(numbers).flatMap(x->Observable.just(x+"<>"));
        observable.subscribe(System.out::println);


        System.out.println("=====================================");

        Flowable<String> flowable = Flowable.just("A", "", "B", "", "c")
                .doOnNext(s->System.out.println("doOnNext() : " + s))
                .flatMap(data->
                {
                    if("".equals(data)){
                        return Flowable.empty();
                    }else{
                        return Flowable.just(data.toLowerCase());
                    }
                });

        flowable.subscribe(System.out::println);

        System.out.println("=====================================");



    }
}