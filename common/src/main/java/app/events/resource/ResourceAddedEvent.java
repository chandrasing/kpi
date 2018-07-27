package app.events.resource;

import app.events.AbstractEvent;

public class ResourceAddedEvent extends AbstractEvent {
    private final String name;
    private final String type;
    private final String unit;

    public ResourceAddedEvent(String id, String name, String type, String unit) {
        super(id);
        this.name = name;
        this.type = type;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "ResourceAddedEvent{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
