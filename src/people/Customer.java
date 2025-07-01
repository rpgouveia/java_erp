package people;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sales.SalesOrder;

public class Customer {
    // atributos
    private int id;
    private String name;
    private String CPF;
    private String email;
    private String phone;
    private String address;
    private Date registrationDate;

    // arrayList na classe modelo
    private ArrayList<SalesOrder> purchaseHistory;
    private ArrayList<String> preferences;
    private ArrayList<String> observations;

    // construtor
    public Customer() {
        this.purchaseHistory = new ArrayList<>();
        this.preferences = new ArrayList<>();
        this.observations = new ArrayList<>();
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    // getters e setters para as listas
    public ArrayList<SalesOrder> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(ArrayList<SalesOrder> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences = preferences;
    }

    public ArrayList<String> getObservations() {
        return observations;
    }

    public void setObservations(ArrayList<String> observations) {
        this.observations = observations;
    }

    // métodos para manipular os arrayLists
    public void addPurchase(SalesOrder salesOrder) {
        this.purchaseHistory.add(salesOrder);
    }

    public void addPreference(String preference) {
        this.preferences.add(preference);
    }

    public void addObservation(String observation) {
        this.observations.add(observation);
    }

    // método para calcular total gasto pelo cliente
    public double getTotalSpent() {
        double total = 0.0;
        for (SalesOrder order : purchaseHistory) {
            total += order.getTotalValue();
        }
        return total;
    }

    // método para contar número de compras
    public int getPurchaseCount() {
        return purchaseHistory.size();
    }
}