package app.resource.commands;

public enum ResourceCommandFactory {
    INSTANCE;

    public AddResourceCommand newAddResourceCommand(String id, String name, String type, String unit) {
        return new AddResourceCommand(id, name, type, unit);
    }
}
