package app.resource.repository;

import app.repo.WriteOnlyRepository;
import app.resource.entity.ResourceEntity;

public interface ResourceWriteOnlyRepository extends WriteOnlyRepository<ResourceEntity, String> { }
