package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository
        extends MongoRepository<UserMongoDocument, String> {
}