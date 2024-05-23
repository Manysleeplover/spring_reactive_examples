package ru.romanov.reactive.example.temperatureexample;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public class RxSeeEmitter extends SseEmitter {
    static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
    private final Observer<Temperature> subscriber; // (1)
    RxSeeEmitter() {
        super(SSE_SESSION_TIMEOUT); // (2)
        this.subscriber = new Observer<>() { // (3)


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Temperature temperature) {
                try {
                    RxSeeEmitter.this.send(temperature); // (4)
                } catch (IOException e) {
                    onError(e); // (5)
                }
            }
            @Override
            public void onError(@NonNull Throwable e) { } // (6)

            @Override
            public void onComplete() {

            }

        };
        onCompletion(subscriber::onComplete); // (8)
        onTimeout(() -> {}); // (9)
    }
    Observer<Temperature> getSubscriber() { // (10)
        return subscriber;
    }
}