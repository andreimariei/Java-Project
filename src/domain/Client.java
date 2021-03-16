package domain;

public class Client extends Entity<String> {
    private String name;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Client(String name) {
        this.name = name;
    }
}
