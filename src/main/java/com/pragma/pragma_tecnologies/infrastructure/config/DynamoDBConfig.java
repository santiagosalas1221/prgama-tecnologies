package com.pragma.pragma_tecnologies.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@Configuration
public class DynamoDBConfig {

        @Bean
        public DynamoDbAsyncClient dynamoDbAsyncClient() {
            return DynamoDbAsyncClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(ProfileCredentialsProvider.create("bdrdoghouse")) // Usar el perfil de AWS
                    .build();
        }
    }