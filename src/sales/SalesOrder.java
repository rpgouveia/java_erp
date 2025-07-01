package sales;

import java.util.ArrayList;
import java.util.Date;

public class SalesOrder {
    // atributos
    private int orderNumber;
    private Date orderDate;
    private double totalValue;
    private String status;
    private double discount;

    // arrayList na classe modelo
    private ArrayList<SalesItem> salesItems;
    private ArrayList<String> statusHistory;
    private ArrayList<String> comments;

    // construtor
    public SalesOrder() {
        this.salesItems = new ArrayList<>();
        this.statusHistory = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.statusHistory.add("Criado em " + new Date());
    }

    // getters e setters
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.statusHistory.add("Status alterado para: " + status + " em " + new Date());
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // getters e setters para arrayLists
    public ArrayList<SalesItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(ArrayList<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }

    public ArrayList<String> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(ArrayList<String> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    // métodos para manipular os arrayLists
    public void addSalesItem(SalesItem item) {
        this.salesItems.add(item);
        recalculateTotal();
    }

    public void addComment(String comment) {
        this.comments.add(comment + " - " + new Date());
    }

    // método para recalcular total baseado nos itens
    public void recalculateTotal() {
        double total = 0.0;
        for (SalesItem item : salesItems) {
            total += item.getSubtotal();
        }
        this.totalValue = total - this.discount;
    }

    // método para contar número de itens
    public int getItemCount() {
        return salesItems.size();
    }

    // método para obter quantidade total de produtos
    public int getTotalQuantity() {
        int total = 0;
        for (SalesItem item : salesItems) {
            total += item.getQuantity();
        }
        return total;
    }
}