package com.pasterz.hotel.springangulardemo.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.pasterz.hotel.springangulardemo.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
