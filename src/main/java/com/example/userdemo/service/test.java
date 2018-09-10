package com.example.userdemo.service;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-18 下午3:58
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Deprecated
public class test {
    public static void main(String[] args) {
        List<String> a = Arrays.asList("a", "a", "d", "ds");
        Subscription subscribe = rx.Observable.from(a).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });

        Subscription subscribe1 = Observable.from(a).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        });

        Observable.from(a).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        });
        List<String> collect = a.parallelStream().filter(s -> {
            if (s == "1") {
                System.out.println("s = " + s);
            }
            return true;
        }).collect(Collectors.toList());

    }
}
