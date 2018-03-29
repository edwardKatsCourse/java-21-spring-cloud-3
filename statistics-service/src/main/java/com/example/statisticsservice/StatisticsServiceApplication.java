package com.example.statisticsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class StatisticsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsServiceApplication.class, args);
    }
}

@Component
class DummyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of(1L, 2L, 3L)
                .map(x -> Statistic.builder()
                        .userId(x)
                        .amount(100.00)
                        .type("income")
                        .build())
                .forEach(x -> statisticRepository.save(x));
    }
}
