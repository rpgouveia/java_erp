package catalog;

import java.util.ArrayList;

public class Category {
    // atributos
    private int id;
    private String name;
    private String description;

    // arrayList na classe modelo
    private ArrayList<Product> products;
    private ArrayList<String> keywords;
    private ArrayList<String> subcategories;

    // construtor
    public Category() {
        this.products = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.subcategories = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getters e setters para arrayLists
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(ArrayList<String> subcategories) {
        this.subcategories = subcategories;
    }

    // métodos para manipular os arrayLists
    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public void addSubcategory(String subcategory) {
        this.subcategories.add(subcategory);
    }

    // método para obter produtos em estoque baixo
    public ArrayList<Product> getProductsWithLowStock() {
        ArrayList<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getStockQuantity() <= product.getMinimumStock()) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    // método para contar produtos
    public int getProductCount() {
        return products.size();
    }

    // método para calcular valor total em estoque
    public double getTotalStockValue() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getSalesPrice() * product.getStockQuantity();
        }
        return total;
    }
}