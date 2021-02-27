package com.commax.debugging;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MyClass {
    public static void main(String[] args){



        System.out.println("doOnEach");
        /**
         * doOnEach
         */
        String[]data = {"ONE","TWO","THREE"};
        Observable<String> doOnEach = Observable.fromArray(data);

        doOnEach.doOnEach(noti->{
            if(noti.isOnNext()) System.out.println("onNext :"+noti.getValue());
            if(noti.isOnComplete()) System.out.println("onComplete");
            if(noti.isOnError()) System.out.println("onError : "+noti.getError().getMessage());
        }).subscribe(System.out::println);

/*
        System.out.println("===========================================================");
        System.out.println("onErrorReturn");

        String[] grades={"70", "88", "&100", "93", "83"};
        Observable<Integer> errorReturn = Observable.fromArray(grades)
                .map(d->Integer.parseInt(d))
                .onErrorReturn(e->{
                    if(e instanceof NumberFormatException){
                        e.printStackTrace();
                    }
                    return -1;
                });

        errorReturn.subscribe(s->{
            if(s<0){
                System.out.println("wrong data found");
                return ;
            }
            System.out.println("Grade is :"+s);
        });



 */


        /*
        System.out.println("===========================================================");
        System.out.println("doOnError");
        Integer[] divider={10, 5, 0};
        Observable<Integer> doOnError = Observable.fromArray(divider)
                .map(s->1000/s)
                .doOnNext(s->System.out.println("doOnNext:" + s))
                .doOnComplete(()->System.out.println("doOnComplete"))
                .doOnError(s->System.out.println("doOnError:" + s.getMessage()));

        doOnError.subscribe(s->System.out.println(s));

         */


        System.out.println("===========================================================");
        System.out.println("<onErrorResumeNext>");

        String[] salesData={"100","200","A300"}; //A300은 에러데이터
        //(1)
        Observable<Integer> onParseError=Observable.defer(()->{
           System.out.println("send email to administarator");
            return Observable.just(-1);
        }).subscribeOn(Schedulers.io()); // IO 스케줄러에서 실행됨

        //(2)
        Observable<Integer> source=Observable.fromArray(salesData)
                .map(Integer::parseInt)
                .onErrorResumeNext(onParseError);

        source.subscribe(x->{
            if(x<0){
                System.out.println("wrong data found");
                return;
            }
            System.out.println("Sales data :" + x);
        });
        sleep(1000);

/*





        System.out.println("===========================================================");
        System.out.println("<retry>");


        CommonUtils.exampleStart();
        String url="https://api.github.com/zen";
        Observable<String> retry=Observable.just(url)
                .map(OkHttpHelper::get)
                .retry(5)
                .onErrorReturn(e->CommonUtils.ERROR_CODE);

        retry.subscribe(s->System.out.println("result : "+data));


 */

    }










    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}