package sales;

// Representa um item de venda
public class SalesItem {

    // atributos
    private int id;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    // construtores
    public SalesItem() {
    }

    public SalesItem(int id, int quantity, double unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = calculateSubtotal();
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.subtotal = calculateSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calculateSubtotal() {
        return this.quantity * this.unitPrice;
    }

    public boolean validateItem() {
        // validações básicas do item
        if (quantity <= 0) {
            System.out.println("❌ Quantidade deve ser maior que zero");
            return false;
        }

        if (unitPrice < 0) {
            System.out.println("❌ Preço unitário não pode ser negativo");
            return false;
        }

        if (id <= 0) {
            System.out.println("❌ ID do item deve ser maior que zero");
            return false;
        }

        // verificar se o subtotal está correto
        double expectedSubtotal = calculateSubtotal();
        if (Math.abs(subtotal - expectedSubtotal) > 0.01) { // Tolerância para arredondamento
            System.out.println("❌ Subtotal incorreto");
            return false;
        }

        return true;
    }

    // sobreescrever o método toString para melhorar a exibição
    @Override
    public String toString() {
        return String.format("SalesItem{id=%d, quantity=%d, unitPrice=%.2f, subtotal=%.2f}",
                id, quantity, unitPrice, subtotal);
    }
}