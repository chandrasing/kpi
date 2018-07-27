package app.account.aggregate;

public class AccountOwner {
    private String name;

    public AccountOwner() {}

    public AccountOwner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AccountOwner{" +
                "name='" + name + '\'' +
                '}';
    }
}
