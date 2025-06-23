package financial;

public class PaymentMethod {
    // atributos
    private int id;
    private String type;
    private String description;
    private boolean active;

    // métodos Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getActive não faz sentido, farei isActive no próximo exercício

    public void setActive(boolean active) {
        this.active = active;
    }
}
