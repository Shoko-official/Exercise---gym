package model;

/**
 * Since I want my github to be fully in english, I will write the comments in
 * english.
 * Represents a service sold by the gym (ex: Sauna, Coach...).
 */
public class Service {
    private String code;
    private String label;
    private double price;

    public Service(String code, String label, double price) {
        this.code = code;
        this.label = label;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", price=" + price +
                '}';
    }
}
