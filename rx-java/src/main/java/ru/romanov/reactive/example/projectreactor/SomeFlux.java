package ru.romanov.reactive.example.projectreactor;

import reactor.core.publisher.Flux;

public class SomeFlux {

    public Flux<Integer> getSomeFlux(){
        return Flux.range(1, 1000000);
    }
}
