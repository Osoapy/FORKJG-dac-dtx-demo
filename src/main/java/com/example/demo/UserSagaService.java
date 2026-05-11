package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSagaService {

    @Autowired
    private UserJpaDao userJpaDao;

    @Autowired
    private OutboxRepository outboxRepository;

    public void createUser(String name) {

        UserEntity user = new UserEntity(name);

        userJpaDao.save(user);

        OutboxEvent event = new OutboxEvent(
                String.valueOf(user.getId()),
                "USER_CREATED",
                user.getName(),
                "PENDING"
        );

        outboxRepository.save(event);
    }
}