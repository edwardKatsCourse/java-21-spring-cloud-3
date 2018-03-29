package com.example.statisticsservice;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Statistic {

    @Id
    @GeneratedValue
    private Long statisticId;

    @Column(nullable = false)
    private Long userId;

    private Double amount;

    private String type; //expense - income

}
