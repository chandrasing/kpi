package app.consumer.commands;

public enum ConsumerCommandFactory {
    INSTANCE;

    public AddConsumerCommand newAddConsumerCommand(String id, String name) {
        return new AddConsumerCommand(id, name);
    }
}
