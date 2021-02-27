package com.ygha.debounce;

import com.ygha.common.CommonUtils;
import com.ygha.common.Log;
import com.ygha.common.MarbleDiagram;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static com.ygha.common.Shape.BLUE;
import static com.ygha.common.Shape.GREEN;
import static com.ygha.common.Shape.RED;
import static com.ygha.common.Shape.YELLOW;

public class MyClass implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] data = {"RED", "YELLOW", "GREEN", "BLUE"};

        Observable<String> source = Observable.concat(
                Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[0]),
                Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[1]),
                Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[2]),
                Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[3]))
                .debounce(200L, TimeUnit.MILLISECONDS);

        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        MyClass demo = new MyClass();
        demo.marbleDiagram();
    }
}