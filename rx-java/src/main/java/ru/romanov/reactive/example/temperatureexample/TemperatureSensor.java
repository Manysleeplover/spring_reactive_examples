package ru.romanov.reactive.example.temperatureexample;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component // (1)
public class TemperatureSensor {
    private final Random rnd = new Random(); // (2)
    private final Observable<Temperature> dataStream = // (3)
            Observable
                    .range(0, Integer.MAX_VALUE) // (4)
                    .concatMap(tick -> Observable // (5)
                            .just(tick) // (6)
                            .delay(rnd.nextInt(5000), MILLISECONDS) // (7)
                            .map(tickValue -> this.probe())) // (8)
                    .publish() // (9)
                    .refCount(); // (10)
    private Temperature probe() {
        return new Temperature(16 + rnd.nextGaussian() * 10); // (11)
    }
    public Observable<Temperature> temperatureStream() { // (12)
        return dataStream;
    }
}