package app.resource.repository;

import app.repo.ReadOnlyRepository;
import app.resource.entity.ResourceEntity;

public interface ResourceReadOnlyRepository extends ReadOnlyRepository<ResourceEntity, String> { }
