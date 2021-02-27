package com.ygha.zipwith;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class MyClass {

    public void observableZipTest(){

        zipStream1().zipWith(zipStream2(), (a,b)->a+"-"+b)
                .subscribe(s->System.out.println(s));

    }

    private Observable<String> zipStream1(){
        final List<String> items1 = Arrays.asList("one","two","three");
        return Observable.fromIterable(items1);
    }

    private Observable<String> zipStream2(){
        final List<String> items1 = Arrays.asList("five","six","seven");
        return Observable.fromIterable(items1);
    }

    public static void main(String[] args){
        MyClass myClass = new MyClass();
        myClass.observableZipTest();
    }
}