package com.commax.flat_concat_map;

import com.ygha.common.CommonUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import sun.rmi.runtime.Log;

public class MyClass {
    public static void main(String[] args) {


        System.out.println("<switchMap>");
        CommonUtils.exampleStart();

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(s->System.out.println("doOnNext :" + s))//중간결과 확인용 함수
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );

        source.subscribe(s->System.out.println("subscribe :"+s));
        CommonUtils.sleep(2000);

        /**
         * 원은 100ms 단위로 발생되고, <> 은 200ms 단위로 발생하기 때문에 1이 발행되기 전에 5가 발행되버렸다. 그러면
         * 중간에 있는 3은 발행 취소 되버리고, 마지막 5만 처리한 결과은 5<> 만 두번 발행된다.
         *
         */
    }

}