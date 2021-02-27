package com.ygha.concatwith;

import com.ygha.common.CommonUtils;
import com.ygha.common.Log;
import com.ygha.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.ygha.common.CommonUtils.GITHUB_ROOT;

public class MyClass {


    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_heaven";

    public void usingConcat() {
        CommonUtils.exampleStart();
        Observable<String> source = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get)
                .concatWith(Observable.just(SECOND_URL)
                        .map(OkHttpHelper::get));
        source.subscribe(Log::it);
        CommonUtils.sleep(5000);
        CommonUtils.exampleComplete();
    }

    public void usingZip() {
        CommonUtils.exampleStart();
        Observable<String> first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);
        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        Observable.zip(first, second,
                (a, b) -> ("\n>>" + a + "\n>>" + b))
                .subscribe(Log::it);
        CommonUtils.sleep(5000);
    }

    public static void main(String[] args) {
        MyClass demo = new MyClass();
//		demo.usingConcat();
        demo.usingZip();
        System.out.println("------------------------------------");
        demo.usingConcat();
    }

}