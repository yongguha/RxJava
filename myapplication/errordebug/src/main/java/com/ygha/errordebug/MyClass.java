package com.ygha.errordebug;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MyClass {

    public static void main(String[] args){

        String[] grades={"70", "88", "&810", "93", "100"};

        //Observable<Integer> error= Observable.just(10);
        Observable<Integer> error= Observable.defer(()->{
            System.out.println("send email to administrator");
            return Observable.just(-1);
                }).subscribeOn(Schedulers.io());

        Observable<Integer> errorReturn = Observable.fromArray(grades)
                .map(i->Integer.parseInt(i))
                .onErrorResumeNext(e->error);

        errorReturn.subscribe(s-> {
                if(s<0) {
                    System.out.println("wrong data found");
                    return;
                }
                System.out.println("Scale data:" + s);
        });





    }
}