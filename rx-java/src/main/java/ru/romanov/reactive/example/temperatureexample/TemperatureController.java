package ru.romanov.reactive.example.temperatureexample;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TemperatureController {
    private final TemperatureSensor temperatureSensor; // (1)
    public TemperatureController(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }
    @RequestMapping(value = "/temperature-stream",
            method = RequestMethod.GET)
    public SseEmitter events(HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter(); // (2)
        temperatureSensor.temperatureStream() // (3)
                .subscribe(emitter.getSubscriber()); // (4)
        return emitter; // (5)
    }
}