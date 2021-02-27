package com.commax.zip;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class MyClass {

    static String getSuffix(String shape){
        if(shape.equalsIgnoreCase("PENTAGON")){
            return  "-P";
        }
        if(shape.equalsIgnoreCase("START")){
            return  "-S";
        }
        return "-B";
    }

    static String getNum(String num){
        if(num.indexOf("-")>0){
            return num.substring(0, num.indexOf("-"));
        }
        return num;
    }

    public static void main(String[] args){
        String[] shapes={"BALL", "PENTAGON", "START"};
        //String[] colorTriangles={"2-T", "4-T", "6-T"};
        String[] colorTriangles={"2-T", "6-T"};

        Observable<String> source = Observable.zip(Observable.fromArray(shapes).map(MyClass::getSuffix),
                Observable.fromArray(colorTriangles).map(MyClass::getNum),(suffix, num)->num+suffix);
        source.subscribe(s->System.out.println(s));

        System.out.println("=======================================================================");


        Observable source2 = Observable.zip(Observable.interval(200L, TimeUnit.MILLISECONDS),
                Observable.just("RED", "GREEN", "BLUE"),
                (x,y)->x+"-"+y);
        source2.subscribe(x->System.out.println("subscribe :"+x));
        try {
            Thread.sleep(1000);
        }catch (Exception e){e.printStackTrace();}

        System.out.println("=======================================================================");
        System.out.println("zipWith");

        Observable<Integer> zipwithSource = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (x,y)->x+y  ).zipWith(Observable.just(1,2,3), (i,j)->i+j);

        zipwithSource.subscribe(s->System.out.println("subscriber:"+s));


        Observable<Integer> zipwithSource2 = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (x,y)->x+y  );

        zipwithSource2.zipWith(Observable.just(1,2,3), (i,j)->i+j);

        zipwithSource.subscribe(s->System.out.println("subscriber:"+s));




    }



}
