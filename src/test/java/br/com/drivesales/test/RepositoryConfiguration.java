/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author thomas
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"br.com.drivesales.domain"})
@EnableJpaRepositories(basePackages = {"br.com.drivesales.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
    
}
