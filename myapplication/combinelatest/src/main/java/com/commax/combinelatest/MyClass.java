package com.commax.combinelatest;

import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

public class MyClass {
    public static void main(String[] args){

        new MyClass().run();
    }

    /**
     * combineLatest 함수는 2개 이상의 Observable을 기반으로 Observable각각의 값이 변경 되었을 때 갱신해주는 함수이다.
     * 마지막 인자로 combiner가 들어가는데 이것이 각 Observable과 결합하여 어떤 결과를 만들어주는 역할을 한다.
     * 첫번째 Observable과 두 번째 Observable을 결합하는 기능을 만든다고 하면, 첫번째 Observable의 값 혹은
     * 두번째 Observable의 값이 변경 되었을 때 그 값을 자동으로 갱신해준다.
     * 첫번째 Observable에서만 데이터를 발행하거나, 두번째 Observable의 데이터 흐름만 있는 경우에는
     * 구독자에게 어떤 데이터도 발행되지 않는다. 두 Observable 모두 값을 발행하면, 그때는 그 값이 방출된다.
     * 그 다음부터는 둘 중 어떤 것이 갱신되던지 최신 결과값을 보여준다.
     */

    private void run(){
        ConnectableObservable<String> source = userInput();
        Observable<Integer> a = source.filter(str-> str.startsWith("a:"))
                .map(str-> str.replace("a:",""))
                .map(Integer::parseInt);

        Observable<Integer> b = source.filter(str-> str.startsWith("b:"))
                .map(str-> str.replace("b:",""))
                .map(Integer::parseInt);

        Observable.combineLatest(a.startWith(0),b.startWith(0),
                (x,y)-> x+y)
                .subscribe(res -> System.out.println("Result : "+res)); source.connect();

    }

    private ConnectableObservable<String> userInput(){
        return Observable.create((ObservableEmitter<String> emitter) -> {
            Scanner in = new Scanner(System.in);
            while (true){ System.out.println("Input: ");
            String line = in.nextLine();
            emitter.onNext(line);
            if(line.indexOf("exit")>=8){
                in.close(); break;
            }
           }
        }).publish();
    }

}