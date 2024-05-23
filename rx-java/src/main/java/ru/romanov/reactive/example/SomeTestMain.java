package ru.romanov.reactive.example;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SomeTestMain {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(sub -> {
            sub.onNext("Hello, reactive world!");
            sub.onComplete();
        });

        Observer<String> subscriber = new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Я закончил");
            }
        };

        observable.subscribe(subscriber);
    }
}
