package com.ygha.fromarray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MyClass {

    public static void main(String[] args){
        Integer[] arr = {100, 200, 300};
        /*
        Observable.fromArray(arr)
                .subscribe(System.out::println);
                */
        Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(System.out::println);

        List<String> names = new ArrayList<>();
        names.add("Jerry");
        names.add("William");
        names.add("Bob");

        Observable<String> source2 = Observable.fromIterable(names);
        source2.subscribe(System.out::println);





    }

}