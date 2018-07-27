package app.resource.handler;

import app.events.resource.ResourceAddedEvent;
import app.resource.entity.ResourceEntity;
import app.resource.repository.ResourceWriteOnlyRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("resourceEvents")
public class ResourceEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ResourceWriteOnlyRepository repository;

    public ResourceEventHandler(ResourceWriteOnlyRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ResourceAddedEvent event) {
        ResourceEntity createdProduct = repository.save(new ResourceEntity(
                event.getId(),
                event.getName(),
                event.getType(),
                event.getUnit()
        ));

        logger.info("Resource {} is saved", createdProduct);
    }
}
