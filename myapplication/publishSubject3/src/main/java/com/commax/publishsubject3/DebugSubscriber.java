package com.commax.publishsubject3;

import io.reactivex.subscribers.DisposableSubscriber;

public class DebugSubscriber<T> extends DisposableSubscriber<T> {

    private String label;

    public DebugSubscriber() {
        super();
    }

    public DebugSubscriber(String label) {
        super();
        this.label = label;
    }

    @Override
    public void onNext(T data) {
        String theadName = Thread.currentThread().getName();
        if(label==null){
            System.out.println(theadName+":" +data);
        }else{
            System.out.println(theadName+":" + label + ":" + data);
        }

    }

    @Override
    public void onError(Throwable throwable) {
        String theadName = Thread.currentThread().getName();
        if(label==null){
            System.out.println(theadName+": 에러 =" + throwable);
        }else{
            System.out.println(theadName+":" + label + ": 에러 =" + throwable);
        }
    }

    @Override
    public void onComplete() {
        String theadName = Thread.currentThread().getName();
        if(label==null){
            System.out.println(theadName+": 완료" );
        }else{
            System.out.println(theadName+":" + label + ": 완료" );
        }
    }
}
