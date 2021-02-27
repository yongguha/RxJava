package com.ygha.single;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class MyClass {

    public static void main(String[] args) {

        Observable<String> source = Observable.just("Hello World");

        Single.fromObservable(source)
                .doOnSuccess(s->System.out.println("doOnSuccess"))
                .subscribe(s->System.out.println(s));

        Observable.just("Hello World")
                .single("default item")
                .subscribe(System.out::println);

        String[] colors ={"RED", "GREEN", "BLUE"};
        Observable.fromArray(colors)
                .first("default item")
                .subscribe(System.out::println);

        Observable.empty()
                .single("default item")
                .subscribe(System.out::println);

    }

}