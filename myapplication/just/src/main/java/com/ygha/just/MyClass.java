package com.ygha.just;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

public class MyClass {
    public static void main(String[] args){
        Observable.just(1,2,3,4).subscribe(s->System.out.println(s));

    }
}