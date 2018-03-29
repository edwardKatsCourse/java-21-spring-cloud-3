package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}

@Component
class DummyUsersInitialization implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of("peter", "sarah", "michael", "jimmy", "david",
                "jane")
                .map(x -> User.builder()
                        .username(x)
                        .firstName(capitalizeName(x))
                        .lastName(capitalizeName(x) + "s")
                        .build())
                .forEach(x -> userRepository.save(x));
    }

    private static String capitalizeName(String initialName) {
        String firstLetter = initialName.substring(0, 1);
        String nameWithoutFirstChat = initialName.substring(1, initialName.length());
        return firstLetter.toUpperCase() + nameWithoutFirstChat;
    }

}
