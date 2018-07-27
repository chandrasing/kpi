package app.events.consumer;

import app.events.AbstractEvent;

public class ConsumerAddedEvent extends AbstractEvent {
    private final String name;

    public ConsumerAddedEvent(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
