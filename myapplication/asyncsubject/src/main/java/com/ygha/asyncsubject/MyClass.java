package com.ygha.asyncsubject;




import io.reactivex.Observable;


public class MyClass {
    public static void main(String[] args){

        Float[] temperature = {10.1f, 13.4f, 12.f};


        /*
        Observable<Float> source = Observable.fromArray(temperature);
        source.doOnNext(s->System.out.println("doOnNext"+s))
        .subscribe(s->System.out.println(s));


        Observable<Float> source = Observable.fromArray(temperature)
                .doOnNext(s->System.out.println("doOnNext"+s));
        source.subscribe(s->System.out.println(s));
        */


        String[]data = {"ONE","TWO","THREE"};
        Observable<String> source = Observable.fromArray(data);

        source.doOnEach(noti->{
            if(noti.isOnNext()) System.out.println("onNext :"+noti.getValue());
            if(noti.isOnComplete()) System.out.println("onComplete");
            if(noti.isOnError()) System.out.println("onError : "+noti.getError().getMessage());
        }).subscribe(System.out::println);



    }


}