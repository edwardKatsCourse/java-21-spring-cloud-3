package com.example.gatewayservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
@RequestMapping("/users")
class UserRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/info")
    public Collection<UserResponse> getUsersInfo() {
        List<UserResponse> collect = restTemplate.exchange("http://account-service/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Resources<UserResponse>>() {
                })
                .getBody()
                .getContent()
                .stream()
//                .map(x -> x.getUsername())
                .collect(Collectors.toList());


        return collect;
    }


}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserResponse {
    private String username;
    private String firstName;
    private String lastName;
}