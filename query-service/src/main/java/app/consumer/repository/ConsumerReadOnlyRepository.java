package app.consumer.repository;

import app.consumer.entity.ConsumerEntity;
import app.repo.ReadOnlyRepository;

public interface ConsumerReadOnlyRepository extends ReadOnlyRepository<ConsumerEntity, String> { }
