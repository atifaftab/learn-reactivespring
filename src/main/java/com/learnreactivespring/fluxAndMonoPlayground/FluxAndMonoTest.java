package com.learnreactivespring.fluxAndMonoPlayground;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

    @Test
    public void fluxTestElements_WithoutError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot")
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot")
                .concatWith(Flux.error(new RuntimeException("Runtime error")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Runtime error")
                .verify();
    }

    @Test
    public void stringMono() {
        Mono<String> stringMono = Mono.just("Spring");
        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();
    }
}
