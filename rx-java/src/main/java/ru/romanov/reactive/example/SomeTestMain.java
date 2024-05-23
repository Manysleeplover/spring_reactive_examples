package ru.romanov.reactive.example;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SomeTestMain {
    public static void main(String[] args) {
        Disposable subscribe = Observable.create(
                sub -> {
                    sub.onNext("Hello, reactive world!");
                    sub.onComplete();
                }
        ).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Done!")
        );

        System.out.println(subscribe);
    }
}
