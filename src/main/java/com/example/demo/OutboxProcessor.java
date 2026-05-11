package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxProcessor {

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private UserMongoRepository mongoRepository;

    @Autowired
    private UserJpaDao userJpaDao;

    @Scheduled(fixedDelay = 5000)
    public void process() {

        List<OutboxEvent> events =
                outboxRepository.findByStatus("PENDING");

        for (OutboxEvent event : events) {

            try {

                UserMongoDocument mongoUser =
                        new UserMongoDocument(
                                event.getAggregateId(),
                                event.getPayload()
                        );

                mongoRepository.save(mongoUser);

                event.setStatus("COMPLETED");

                outboxRepository.save(event);

            } catch (Exception e) {

                UserEntity user =
                        userJpaDao.findById(
                                Long.parseLong(
                                        event.getAggregateId()
                                )
                        );

                if (user != null) {
                    userJpaDao.delete(user.getId());
                }

                event.setStatus("FAILED");

                outboxRepository.save(event);
            }
        }
    }
}