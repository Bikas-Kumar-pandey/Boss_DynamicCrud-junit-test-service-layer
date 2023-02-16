package com.bosch.mongo.MongoConfig;

import com.bosch.mongo.repository.CategoryRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = CategoryRepository.class)
@Configuration
public class MongodbConfig {
}