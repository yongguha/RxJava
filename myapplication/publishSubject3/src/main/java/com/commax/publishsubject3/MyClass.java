package com.commax.publishsubject3;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.PublishSubject;

public class MyClass {

    public static void main(String[] args){

        PublishSubject<Integer> subject = PublishSubject.<Integer>create();

        subject.filter(data->(data%2)!=0)
                .subscribe(values->System.out.println(values));

        subject.onNext(0);
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
        subject.onNext(5);


        System.out.println("=============================================");

        TemperatureManager manager = new TemperatureManager();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            int nextTemperature = (new Random()).nextInt(15) + 10;
            manager.setTemperature(new TemperatureManager.Temperature(nextTemperature));
        }, 0L, 3, TimeUnit.SECONDS);

        manager.updateEvent().subscribe(s->System.out.println("현재온도 : " +s.getDegree()));






    }
}