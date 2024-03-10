package school.sptech.neosspringjava.services;

public class Service {
    private String name;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
