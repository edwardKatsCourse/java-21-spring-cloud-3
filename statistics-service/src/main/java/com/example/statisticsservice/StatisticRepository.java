package com.example.statisticsservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
