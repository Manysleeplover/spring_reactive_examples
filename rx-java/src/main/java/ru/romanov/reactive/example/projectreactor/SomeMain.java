package ru.romanov.reactive.example.projectreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

@Slf4j
public class SomeMain {
    public static void main(String[] args) throws InterruptedException {
        Flux.generate( // (1)
                        () -> Tuples.of(0L, 1L), // (1.1)
                        (state, sink) -> { //
                            log.info("generated value: {}", state.getT2()); //
                            sink.next(state.getT2()); // (1.2)
                            long newValue = state.getT1() + state.getT2(); //
                            return Tuples.of(state.getT2(), newValue); // (1.3)
                        })
                .take(99) // (3)
                .subscribe(e -> log.info("onNext: {}", e));
    }
}
