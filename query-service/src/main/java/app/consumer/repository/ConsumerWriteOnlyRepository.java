package app.consumer.repository;

import app.consumer.entity.ConsumerEntity;
import app.repo.WriteOnlyRepository;

public interface ConsumerWriteOnlyRepository extends WriteOnlyRepository<ConsumerEntity, String> { }
