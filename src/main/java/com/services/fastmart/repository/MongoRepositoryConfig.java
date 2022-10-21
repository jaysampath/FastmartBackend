package com.services.fastmart.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.services.fastmart.repository")
public class MongoRepositoryConfig {
}
