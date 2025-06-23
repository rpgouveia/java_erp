package purchasing;

import java.util.Date;

public class PurchaseOrder {
    // atributos
    private int orderNumber;
    private Date orderDate;
    private double totalValue;
    private String status;
    private boolean approved;

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
    }

    // getApproved não faz sentido, acho que isApproved seja mais adequado
    // farei no próximo exercício

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}