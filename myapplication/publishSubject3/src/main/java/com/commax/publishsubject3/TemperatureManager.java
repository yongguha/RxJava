package com.commax.publishsubject3;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class TemperatureManager {

    public static class Temperature{
        private int currentTemperature;

        public Temperature(int currentTemperature) {
            this.currentTemperature = currentTemperature;
        }

        int getDegree(){
            return this.currentTemperature;
        }
    }

    private PublishSubject<Temperature> subject = PublishSubject.create();

    void setTemperature(Temperature temperature){
        subject.onNext(temperature);
    }

    Observable<Temperature> updateEvent(){
        return subject.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());
    }

}
