package app.consumer.handler;

import app.consumer.entity.ConsumerEntity;
import app.consumer.repository.ConsumerWriteOnlyRepository;
import app.events.consumer.ConsumerAddedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("consumerEvents")
public class ConsumerEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ConsumerWriteOnlyRepository repository;

    public ConsumerEventHandler(ConsumerWriteOnlyRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ConsumerAddedEvent event) {
        ConsumerEntity createdProduct = repository.save(new ConsumerEntity(
                event.getId(),
                event.getName()
        ));

        logger.info("Consumer {} is saved", createdProduct);
    }
}
