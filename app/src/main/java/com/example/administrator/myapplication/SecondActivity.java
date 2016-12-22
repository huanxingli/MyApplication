package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/1.
 */

public class SecondActivity extends Activity {

    private Subscriber<String> subscriber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /**
         * 观察者
         */
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Rxjava subscriber onCompleted() -->所在的线程为：" + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Rxjava subscriber onError():" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                System.out.println("Rxjava subscriber onNext():"  + s + "-->所在的线程为： " + Thread.currentThread().getName());

            }

            /**
             * subscriber新增的方法，在subscriber刚开始，但是事件还没有发送前调用
             * 主要做准备工作，但是工作线程是跟onNext()方法线程是一致的
             */
            @Override
            public void onStart() {
                System.out.println("Rxjava subscriber onStart():" + Thread.currentThread().getName());
            }


        };

        /**
         * 被观察者
         */
        Observable observable = Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello1");
                subscriber.onNext("Hello2");
                subscriber.onNext("Hello3");
                System.out.println("Rxjava observable call():" + "-->所在的线程为：" + Thread.currentThread().getName());
                subscriber.onCompleted();
            }
        });
        //快捷创建事件队列
//        Observable observable = Observable.just("Hello1","Hello2","Hello3");

        //快捷创建事件队列
//        String[] words = {"Hello1","Hello2","Hello3"};
//        Observable observable = Observable.from(words);


        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {

            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
            }
        };


        observable.subscribeOn(Schedulers.io())//指定subscribe在IO线程工作，call()方法
                .observeOn(AndroidSchedulers.mainThread()) //指定Subscriber的回调在主线程工作
                .subscribe(subscriber);

//        for (int i=0;i<10;i++){
//            System.out.println("Rxjava " + i);
//        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        //防止内存溢出，因为observable持有subscriber引用
        if (!subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }
    }
}
