package app.consumer.commands;

public class AddConsumerCommand {
    private final String id;
    private final String name;

    AddConsumerCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AddConsumerCommand{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
