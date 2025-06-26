package sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import catalog.Product;
import catalog.Category;
import people.Customer;
import people.Salesperson;
import financial.PaymentMethod;

// Exercício 3
public class SalesService {
    private List<SalesOrder> salesHistory;
    private int nextOrderNumber;

    public SalesService() {
        this.salesHistory = new ArrayList<>();
        this.nextOrderNumber = 1;
    }

    public SalesOrder sellItems(Customer customer, Salesperson salesperson,
                                List<Product> products, List<Integer> quantities,
                                PaymentMethod paymentMethod) {
        System.out.println("=== INICIANDO PROCESSO DE VENDA ===");

        // Criar o pedido de venda com relacionamentos
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setOrderNumber(nextOrderNumber++);
        salesOrder.setOrderDate(new Date());
        salesOrder.setStatus("Em Processamento");

        // Validar se a venda pode ser realizada
        if (!validateSale(products, quantities)) {
            salesOrder.setStatus("Cancelada - Validação Falhou");
            return salesOrder;
        }

        // Calcular o total da venda
        double totalValue = calculateOrderTotal(products, quantities);

        // Aplicar o desconto baseado no método de pagamento
        double discount = calculatePaymentDiscount(paymentMethod, totalValue);
        salesOrder.setDiscount(discount);

        // Valor final após desconto
        double finalValue = totalValue - discount;
        salesOrder.setTotalValue(finalValue);

        // Processar os itens da venda
        List<SalesItem> salesItems = processSalesItems(products, quantities, salesOrder);

        // Finalizar a venda
        salesOrder.setStatus("Finalizada");
        salesHistory.add(salesOrder);

        // Exibir informações da venda realizada
        displaySaleInfo(customer, salesperson, salesOrder, salesItems, paymentMethod);

        System.out.println("✓ Venda realizada com sucesso!");

        return salesOrder;
    }

    private boolean validateSale(List<Product> products, List<Integer> quantities) {
        if (products == null || quantities == null) {
            System.out.println("❌ Erro: Produtos ou quantidades não informados");
            return false;
        }

        if (products.size() != quantities.size()) {
            System.out.println("❌ Erro: Número de produtos e quantidades não conferem");
            return false;
        }

        // Validar estoque para cada produto
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int requestedQuantity = quantities.get(i);

            if (requestedQuantity <= 0) {
                System.out.printf("❌ Erro: Quantidade inválida para %s%n", product.getName());
                return false;
            }

            if (!checkProductAvailability(product, requestedQuantity)) {
                System.out.printf("❌ Erro: Estoque insuficiente para %s%n", product.getName());
                return false;
            }
        }

        System.out.println("✓ Validação da venda aprovada");
        return true;
    }

    private boolean checkProductAvailability(Product product, int requestedQuantity) {
        return product.getStockQuantity() >= requestedQuantity;
    }

    private double calculateOrderTotal(List<Product> products, List<Integer> quantities) {
        double total = 0.0;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            double itemTotal = product.getSalesPrice() * quantity;
            total += itemTotal;

            System.out.printf("Item: %s x%d = R$ %.2f%n",
                    product.getName(), quantity, itemTotal);
        }

        System.out.printf("Subtotal: R$ %.2f%n", total);
        return total;
    }

    private double calculatePaymentDiscount(PaymentMethod paymentMethod, double totalValue) {
        double discountPercentage = 0.0;

        // aplicar desconto conforme o tipo de pagamento
        switch (paymentMethod.getType().toLowerCase()) {
            case "dinheiro":
                discountPercentage = 0.05; // 5%
                break;
            case "pix":
                discountPercentage = 0.03; // 3%
                break;
            case "débito":
                discountPercentage = 0.02; // 2%
                break;
            case "crédito":
                discountPercentage = 0.0; // 0%
                break;
            default:
                discountPercentage = 0.0;
                break;
        }

        double discountValue = totalValue * discountPercentage;

        if (discountValue > 0) {
            System.out.printf("Desconto aplicado (%s): %.1f%% = R$ %.2f%n",
                    paymentMethod.getType(), discountPercentage * 100, discountValue);
        }

        return discountValue;
    }

    private List<SalesItem> processSalesItems(List<Product> products, List<Integer> quantities, SalesOrder salesOrder) {
        List<SalesItem> salesItems = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);

            // criando item de venda
            SalesItem salesItem = new SalesItem();
            salesItem.setId(i + 1);
            salesItem.setQuantity(quantity);
            salesItem.setUnitPrice(product.getSalesPrice());
            salesItem.setSubtotal(product.getSalesPrice() * quantity);

            salesItems.add(salesItem);

            // atualizando estoque do produto
            updateProductStock(product, quantity);
        }

        return salesItems;
    }

    private void updateProductStock(Product product, int soldQuantity) {
        int currentStock = product.getStockQuantity();
        int newStock = currentStock - soldQuantity;
        product.setStockQuantity(newStock);

        System.out.printf("Estoque atualizado - %s: %d → %d%n",
                product.getName(), currentStock, newStock);

        // verificar se atingiu o estoque mínimo
        if (newStock <= product.getMinimumStock()) {
            System.out.printf("ALERTA: %s atingiu estoque mínimo (%d unidades)%n",
                    product.getName(), product.getMinimumStock());
        }
    }

    private void displaySaleInfo(Customer customer, Salesperson salesperson,
                                 SalesOrder salesOrder, List<SalesItem> salesItems,
                                 PaymentMethod paymentMethod) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           COMPROVANTE DE VENDA");
        System.out.println("=".repeat(50));
        System.out.printf("Pedido Nº: %d%n", salesOrder.getOrderNumber());
        System.out.printf("Data: %s%n", salesOrder.getOrderDate());
        System.out.printf("Cliente: %s (CPF: %s)%n", customer.getName(), customer.getCPF());
        System.out.printf("Vendedor: %s%n", salesperson.getName());
        System.out.printf("Método Pagamento: %s%n", paymentMethod.getType());
        System.out.println("-".repeat(50));

        for (SalesItem item : salesItems) {
            System.out.printf("Item %d: Qtd %d x R$ %.2f = R$ %.2f%n",
                    item.getId(), item.getQuantity(), item.getUnitPrice(), item.getSubtotal());
        }

        System.out.println("-".repeat(50));
        System.out.printf("Desconto: R$ %.2f%n", salesOrder.getDiscount());
        System.out.printf("TOTAL: R$ %.2f%n", salesOrder.getTotalValue());
        System.out.println("=".repeat(50));
    }

    // Método para demonstrar durante o teste
    public void demonstrateObjectRelationships() {
        System.out.println("=== DEMONSTRAÇÃO ===\n");

        // criar categoria
        Category electronics = new Category();
        electronics.setId(1);
        electronics.setName("Eletrônicos");
        electronics.setDescription("Produtos eletrônicos e tecnologia");

        // criar produtos com relacionamento à categoria
        Product notebook = createProduct(1, "Notebook Dell Inspiron", "Notebook com 8GB RAM",
                2500.00, 2000.00, 10, 2, "7891234567890");

        Product mouse = createProduct(2, "Mouse Logitech", "Mouse wireless ergonômico",
                85.00, 60.00, 50, 5, "7891234567891");

        Product keyboard = createProduct(3, "Teclado Mecânico", "Teclado mecânico RGB",
                350.00, 250.00, 25, 3, "7891234567892");

        // criar cliente
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("João Silva");
        customer.setCPF("123.456.789-00");
        customer.setEmail("joao.silva@email.com");
        customer.setPhone("(11) 99999-9999");
        customer.setAddress("Rua das Flores, 123");
        customer.setRegistrationDate(new Date());

        // criar vendedor
        Salesperson salesperson = new Salesperson();
        salesperson.setId(1);
        salesperson.setName("Maria Santos");
        salesperson.setCPF("987.654.321-00");
        salesperson.setSalary(3000.00);
        salesperson.setAdmissionDate(new Date());

        // criar método de pagamento
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1);
        paymentMethod.setType("PIX");
        paymentMethod.setDescription("Pagamento instantâneo via PIX");
        paymentMethod.setActive(true);

        // preparar lista de produtos e quantidades para venda
        List<Product> productsToSell = new ArrayList<>();
        productsToSell.add(notebook);
        productsToSell.add(mouse);
        productsToSell.add(keyboard);

        List<Integer> quantities = new ArrayList<>();
        quantities.add(1); // 1 notebook
        quantities.add(2); // 2 mouses
        quantities.add(1); // 1 teclado

        // executar a venda com todos os relacionamentos
        SalesOrder completedSale = sellItems(
                customer,
                salesperson,
                productsToSell,
                quantities,
                paymentMethod
        );

        System.out.printf("\nRelacionamentos demonstrados com sucesso!%n");
        System.out.printf("Venda Nº %d finalizada com status: %s%n",
                completedSale.getOrderNumber(), completedSale.getStatus());
    }

    private Product createProduct(int id, String name, String description,
                                  double salePrice, double purchasePrice,
                                  int stock, int minStock, String barcode) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setSalesPrice(salePrice);
        product.setPurchasePrice(purchasePrice);
        product.setStockQuantity(stock);
        product.setMinimumStock(minStock);
        product.setBarCode(barcode);
        return product;
    }

    // getters para acesso aos dados
    public List<SalesOrder> getSalesHistory() {
        return salesHistory;
    }

    public int getNextOrderNumber() {
        return nextOrderNumber;
    }

    // Teste
    public static void main(String[] args) {
        SalesService service = new SalesService();
        service.demonstrateObjectRelationships();
    }
}