package org.location.controller;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan ({"org.location"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Spring Boot Beans");
            System.out.println("-----------------");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            
            System.out.println("Environment Variables");
            System.out.println("---------------------");
            String[] environmentVariables = System.getProperties().keySet().toArray(new String[0]);
            Arrays.sort(environmentVariables);
            for (String variable : environmentVariables) {
            	System.out.println(variable + " : " + System.getProperty(variable));            	
            }
        };
    }

}