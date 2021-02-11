package com.optum.pathway.poc.security;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {    
    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor() {
        return new PathwayEncryptor();
    }
}