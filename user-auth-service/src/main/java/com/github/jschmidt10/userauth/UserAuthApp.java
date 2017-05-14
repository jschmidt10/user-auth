package com.github.jschmidt10.userauth;

import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Launcher for the user authentication service.
 */
@SpringBootApplication
public class UserAuthApp {

    @Bean
    public UserAuthService service(ServiceLoader<UserAuthRepository> serviceLoader) {
        UserAuthRepository repository = ensureOnlyOne(serviceLoader);
        return new UserAuthService(repository);
    }

    private UserAuthRepository ensureOnlyOne(ServiceLoader<UserAuthRepository> serviceLoader) {
        List<UserAuthRepository> repos = StreamSupport.stream(serviceLoader.spliterator(), false).collect(Collectors.toList());
        if (repos.size() != 1) {
            throw new IllegalStateException("Found " + repos.size() + " UserAuthRepository implementations on the classpath. Expected exactly 1.");
        }
        return repos.get(0);
    }

    @Bean
    public ServiceLoaderFactoryBean factoryBean() {
        ServiceLoaderFactoryBean factory = new ServiceLoaderFactoryBean();
        factory.setServiceType(UserAuthRepository.class);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserAuthApp.class, args);
    }
}
