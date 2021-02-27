package com.commax.zip_electricmeter;

import java.text.DecimalFormat;

import io.reactivex.Observable;
import javafx.util.Pair;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class MyClass {
    private static String[] data ={
            "100",// 910+93.3*100 = 10,240
            "200"// 1600+93.3*200+187.9*100 = 39,050
    };
    private static Observable<Integer> basePrice = Observable.fromArray(data)
            .map(Integer::parseInt)
            .map(s->{
                if(s<=200) return 910;
                if(s<=400) return 1600;
                return 7300;
            });


    private static Observable<Integer> usagePrice = Observable.fromArray(data)
            .map(Integer::parseInt)
            .map(s->{
                double series1 = min(200,s)*93.3;
                double series2 = min(200,max(s-200,0))*187.9;
                double series3 = min(0,max(s-400,0))*280.65;
                return (int)(series1 + series2 + series3);
            });


    private static int index=0;
    public static void main(String[] args){

        Observable source = Observable.zip(basePrice, usagePrice, (x,y)->x+y);
        source.map(s->new DecimalFormat("#, ###").format(s))
                .subscribe(s->
                        {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Usage:"+data[index]+"kwh =>");
                            sb.append("Price:"+s+"won");
                            System.out.println(sb);
                            index++;
                        }
                        );



    }
}