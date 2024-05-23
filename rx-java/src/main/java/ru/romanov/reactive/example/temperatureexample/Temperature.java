package ru.romanov.reactive.example.temperatureexample;

final public class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
