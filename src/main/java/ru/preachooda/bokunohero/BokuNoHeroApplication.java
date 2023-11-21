package ru.preachooda.bokunohero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.preachooda.bokunohero", "ru.preachooda.bokunoherocore"})
@ComponentScan(basePackages = {"ru.preachooda.bokunohero","ru.preachooda.bokunoherocore"})
@EntityScan(basePackages = {"ru.preachooda.bokunohero","ru.preachooda.bokunoherocore"})
public class BokuNoHeroApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BokuNoHeroApplication.class, args);
    }

}
