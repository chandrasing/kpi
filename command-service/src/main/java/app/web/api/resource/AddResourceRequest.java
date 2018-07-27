package app.web.api.resource;

public class AddResourceRequest {
    private String name;
    private String type;
    private String unit;

    public AddResourceRequest() { }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }
}
