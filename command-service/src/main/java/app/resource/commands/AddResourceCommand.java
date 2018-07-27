package app.resource.commands;

public class AddResourceCommand {
    private final String id;
    private final String name;
    private final String type;
    private final String unit;


    public AddResourceCommand(String id, String name, String type, String unit) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit = unit;
    }

    public String getId() {
        return id;
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
        return "AddResourceCommand{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", unit='" + unit + '\'' +
                '}';
    }
}
