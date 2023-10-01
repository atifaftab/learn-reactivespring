package com.learnreactivespring.fluxAndMonoPlayground;


import org.junit.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot")
//                .concatWith(Flux.error(new RuntimeException("Run time exception")))
                .concatWith(Flux.just("Reactive java"))
                .log();
        stringFlux.subscribe(System.out::println,
                System.out::println,
                () -> System.out.println("Completed !"));
    }
}
