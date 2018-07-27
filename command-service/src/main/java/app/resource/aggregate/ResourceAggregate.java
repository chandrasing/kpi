package app.resource.aggregate;

import app.events.consumer.ConsumerAddedEvent;
import app.resource.commands.AddResourceCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;

@Aggregate
public class ResourceAggregate implements Serializable {
    private static final long serialVersionUID = 1L;

    @AggregateIdentifier
    private String id;

    private String name;
    private String type;
    private String unit;

    public ResourceAggregate() { }

    @CommandHandler
    public ResourceAggregate(AddResourceCommand command) {
        String id = command.getId();
        String name = command.getName();

        Assert.hasLength(id, "Missing id");
        Assert.hasLength(name, "Missing name");

        AggregateLifecycle.apply(new ConsumerAddedEvent(id, name));
    }

    @EventSourcingHandler
    public void on(ConsumerAddedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

}
