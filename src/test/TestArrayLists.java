package test;

import java.util.ArrayList;
import java.util.Date;
import catalog.Category;
import catalog.Product;
import people.Customer;
import people.Salesperson;
import sales.SalesOrder;
import sales.SalesItem;
import financial.PaymentMethod;


public class TestArrayLists {

    // arrayLists
    private ArrayList<Customer> customers;
    private ArrayList<Category> categories;
    private ArrayList<Product> products;
    private ArrayList<SalesOrder> allSalesOrders;
    private ArrayList<PaymentMethod> paymentMethods;

    // construtor com inicialização
    public TestArrayLists() {
        this.customers = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.products = new ArrayList<>();
        this.allSalesOrders = new ArrayList<>();
        this.paymentMethods = new ArrayList<>();
    }

    // métodos de criação de listas
    public void createAndAddItemsToArrays() {
        System.out.println("=== CRIANDO E ADICIONANDO ITENS AOS ARRAYS ===");
        createCategories();
        createProducts();
        createCustomers();
        createPaymentMethods();
        createSalesOrders();
        System.out.println("Todos os itens foram criados e adicionados.\n");
    }

    private void createCategories() {
        System.out.println("--- Criando Categorias ---");

        // Categoria 1: Eletrônicos
        Category electronics = new Category();
        electronics.setId(1);
        electronics.setName("Eletrônicos");
        electronics.setDescription("Produtos eletrônicos e tecnologia");
        electronics.addKeyword("tecnologia");
        electronics.addKeyword("eletrônicos");
        electronics.addKeyword("informática");
        electronics.addSubcategory("Computadores");
        electronics.addSubcategory("Periféricos");
        categories.add(electronics);

        // Categoria 2: Móveis
        Category furniture = new Category();
        furniture.setId(2);
        furniture.setName("Móveis");
        furniture.setDescription("Móveis para escritório e casa");
        furniture.addKeyword("móveis");
        furniture.addKeyword("escritório");
        furniture.addKeyword("casa");
        furniture.addSubcategory("Mesas");
        furniture.addSubcategory("Cadeiras");
        categories.add(furniture);

        System.out.printf("%d categorias criadas%n", categories.size());
    }

    private void createProducts() {
        System.out.println("--- Criando Produtos ---");

        Category electronics = categories.get(0);
        Category furniture = categories.get(1);

        // Produtos eletrônicos
        Product notebook = createProduct(
                1,
                "Notebook Dell",
                "Notebook Inspiron 15",
                2500.00,
                5,
                5
        );  // Estoque no limite
        Product mouse = createProduct(
                2,
                "Mouse Logitech",
                "Mouse wireless",
                85.00,
                100,
                10
        ); // Estoque normal
        Product keyboard = createProduct(
                3,
                "Teclado Mecânico",
                "Teclado RGB",
                350.00,
                2,
                3
        );  // Estoque baixo

        electronics.addProduct(notebook);
        electronics.addProduct(mouse);
        electronics.addProduct(keyboard);
        products.add(notebook);
        products.add(mouse);
        products.add(keyboard);

        // Produtos de móveis
        Product desk = createProduct(
                4,
                "Mesa de Escritório",
                "Mesa L 120cm",
                450.00,
                20,
                2
        );
        Product chair = createProduct(
                5,
                "Cadeira Ergonômica",
                "Cadeira com apoio lombar",
                380.00,
                1,
                2
        );  // Estoque baixo

        furniture.addProduct(desk);
        furniture.addProduct(chair);
        products.add(desk);
        products.add(chair);

        System.out.printf("%d produtos criados%n", products.size());
    }

    private void createCustomers() {
        System.out.println("--- Criando Clientes ---");

        // Cliente 1
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("João Silva");
        customer1.setCPF("123.456.789-00");
        customer1.setEmail("joao@email.com");
        customer1.setRegistrationDate(new Date());
        customer1.addPreference("Eletrônicos");
        customer1.addPreference("Tecnologia");
        customer1.addObservation("Cliente fidelizado");
        customer1.addObservation("Prefere pagamento à vista");
        customers.add(customer1);

        // Cliente 2
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Maria Santos");
        customer2.setCPF("987.654.321-00");
        customer2.setEmail("maria@email.com");
        customer2.setRegistrationDate(new Date());
        customer2.addPreference("Móveis");
        customer2.addPreference("Decoração");
        customer2.addObservation("Cliente empresarial");
        customers.add(customer2);

        // Cliente 3
        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setName("Pedro Costa");
        customer3.setCPF("456.789.123-00");
        customer3.setEmail("pedro@email.com");
        customer3.setRegistrationDate(new Date());
        customer3.addPreference("Eletrônicos");
        customer3.addObservation("Cliente VIP");
        customers.add(customer3);

        System.out.printf("%d clientes criados%n", customers.size());
    }

    private void createPaymentMethods() {
        System.out.println("--- Criando Métodos de Pagamento ---");

        PaymentMethod pix = new PaymentMethod();
        pix.setId(1);
        pix.setType("PIX");
        pix.setDescription("Pagamento instantâneo");
        pix.setActive(true);
        paymentMethods.add(pix);

        PaymentMethod cash = new PaymentMethod();
        cash.setId(2);
        cash.setType("Dinheiro");
        cash.setDescription("Pagamento em dinheiro");
        cash.setActive(true);
        paymentMethods.add(cash);

        PaymentMethod credit = new PaymentMethod();
        credit.setId(3);
        credit.setType("Cartão de Crédito");
        credit.setDescription("Pagamento no cartão");
        credit.setActive(true);
        paymentMethods.add(credit);

        System.out.printf("%d métodos de pagamento criados%n", paymentMethods.size());
    }

    // método para criar pedidos de venda
    private void createSalesOrders() {
        System.out.println("--- Criando Pedidos de Venda ---");

        // Pedido 1 - João Silva
        SalesOrder order1 = new SalesOrder();
        order1.setOrderNumber(1);
        order1.setOrderDate(new Date());
        order1.setStatus("Finalizada");
        order1.addComment("Primeira compra do cliente");
        order1.addComment("Entrega expressa solicitada");

        // Adicionando itens ao pedido 1
        SalesItem item1 = new SalesItem(1, 1, 2500.00); // Notebook
        SalesItem item2 = new SalesItem(2, 2, 85.00);   // Mouse
        order1.addSalesItem(item1);
        order1.addSalesItem(item2);

        // Adiciona ao histórico do cliente
        customers.get(0).addPurchase(order1);
        allSalesOrders.add(order1);

        // Pedido 2 - Maria Santos
        SalesOrder order2 = new SalesOrder();
        order2.setOrderNumber(2);
        order2.setOrderDate(new Date());
        order2.setStatus("Em processamento");
        order2.addComment("Cliente empresarial");

        // Adicionando itens ao pedido 2
        SalesItem item3 = new SalesItem(3, 3, 450.00);  // Mesa
        SalesItem item4 = new SalesItem(4, 2, 380.00);  // Cadeira
        order2.addSalesItem(item3);
        order2.addSalesItem(item4);

        customers.get(1).addPurchase(order2);
        allSalesOrders.add(order2);

        // Pedido 3 - Pedro Costa
        SalesOrder order3 = new SalesOrder();
        order3.setOrderNumber(3);
        order3.setOrderDate(new Date());
        order3.setStatus("Finalizada");
        order3.addComment("Cliente VIP - desconto especial");

        // Adicionando itens ao pedido 3
        SalesItem item5 = new SalesItem(5, 1, 350.00);  // Teclado
        order3.addSalesItem(item5);

        customers.get(2).addPurchase(order3);
        allSalesOrders.add(order3);

        System.out.printf("%d pedidos de venda criados%n", allSalesOrders.size());
    }

    // métodos para percorrer listas e demonstrar avaliação
    public void demonstrateArrayTraversal() {
        System.out.println("\n=== PERCORRENDO OS ARRAYS ===\n");
        traverseCategoriesArray();
        traverseProductsArray();
        traverseCustomersArray();
        traverseSalesOrdersArray();
        performArrayAnalysis();
    }

    private void traverseCategoriesArray() {
        System.out.println("--- Percorrendo Array de Categorias ---");

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            System.out.printf("Categoria %d: %s%n", (i + 1), category.getName());
            System.out.printf("  Descrição: %s%n", category.getDescription());
            System.out.printf("  Produtos: %d%n", category.getProductCount());

            System.out.print("  Keywords: ");
            for (String keyword : category.getKeywords()) {
                System.out.print(keyword + " ");
            }
            System.out.println();

            System.out.print("  Subcategorias: ");
            for (String sub : category.getSubcategories()) {
                System.out.print(sub + " ");
            }
            System.out.println("\n");
        }
    }

    private void traverseProductsArray() {
        System.out.println("--- Percorrendo Array de Produtos ---");

        for (Product product : products) {
            System.out.printf("Produto: %s%n", product.getName());
            System.out.printf("  Preço: R$ %.2f%n", product.getSalesPrice());
            System.out.printf("  Estoque: %d unidades%n", product.getStockQuantity());

            // Verificar se precisa de reposição
            if (product.getStockQuantity() <= product.getMinimumStock()) {
                System.out.println("ALERTA: Estoque baixo!");
            }
            System.out.println();
        }
    }

    private void traverseCustomersArray() {
        System.out.println("--- Percorrendo Array de Clientes ---");

        for (Customer customer : customers) {
            System.out.printf("Cliente: %s (CPF: %s)%n", customer.getName(), customer.getCPF());
            System.out.printf("  Compras realizadas: %d%n", customer.getPurchaseCount());
            System.out.printf("  Total gasto: R$ %.2f%n", customer.getTotalSpent());

            System.out.print("  Preferências: ");
            for (String preference : customer.getPreferences()) {
                System.out.print(preference + " ");
            }
            System.out.println();

            System.out.println("  Observações:");
            for (String observation : customer.getObservations()) {
                System.out.println("    - " + observation);
            }
            System.out.println();
        }
    }

    private void traverseSalesOrdersArray() {
        System.out.println("--- Percorrendo Array de Pedidos ---");

        for (SalesOrder order : allSalesOrders) {
            System.out.printf("Pedido Nº %d - Status: %s%n", order.getOrderNumber(), order.getStatus());
            System.out.printf("  Data: %s%n", order.getOrderDate());
            System.out.printf("  Valor Total: R$ %.2f%n", order.getTotalValue());
            System.out.printf("  Itens: %d%n", order.getItemCount());

            System.out.println("  Itens do pedido:");
            for (SalesItem item : order.getSalesItems()) {
                System.out.printf("    - Item %d: %d x R$ %.2f = R$ %.2f%n",
                        item.getId(), item.getQuantity(), item.getUnitPrice(), item.getSubtotal());
            }

            if (!order.getComments().isEmpty()) {
                System.out.println("  Comentários:");
                for (String comment : order.getComments()) {
                    System.out.println("    - " + comment);
                }
            }
            System.out.println();
        }
    }

    // método para demonstrar análises usando os arrays
    private void performArrayAnalysis() {
        System.out.println("--- Análises com os Arrays ---");

        // Análise 1: Cliente que mais gastou
        Customer topCustomer = null;
        double maxSpent = 0.0;
        for (Customer customer : customers) {
            if (customer.getTotalSpent() > maxSpent) {
                maxSpent = customer.getTotalSpent();
                topCustomer = customer;
            }
        }
        if (topCustomer != null) {
            System.out.printf("Cliente que mais gastou: %s (R$ %.2f)%n",
                    topCustomer.getName(), maxSpent);
        }

        // Análise 2: Produto mais caro
        Product mostExpensive = null;
        double maxPrice = 0.0;
        for (Product product : products) {
            if (product.getSalesPrice() > maxPrice) {
                maxPrice = product.getSalesPrice();
                mostExpensive = product;
            }
        }
        if (mostExpensive != null) {
            System.out.printf("Produto mais caro: %s (R$ %.2f)%n",
                    mostExpensive.getName(), maxPrice);
        }

        // Análise 3: Total de vendas
        double totalSales = 0.0;
        for (SalesOrder order : allSalesOrders) {
            totalSales += order.getTotalValue();
        }
        System.out.printf("Total de vendas: R$ %.2f%n", totalSales);

        // Análise 4: Produtos com estoque baixo
        System.out.println("Produtos com estoque baixo:");
        boolean hasLowStock = false;
        for (Product product : products) {
            if (product.getStockQuantity() <= product.getMinimumStock()) {
                System.out.printf("  - %s (Estoque: %d, Mínimo: %d)%n",
                        product.getName(), product.getStockQuantity(), product.getMinimumStock());
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("  Nenhum produto com estoque baixo encontrado.");
        }
    }

    // método auxiliar
    private Product createProduct(int id, String name, String description,
                                  double price, int stock, int minStock) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setSalesPrice(price);
        product.setStockQuantity(stock);
        product.setMinimumStock(minStock);
        return product;
    }

    // getters para os arrays
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<SalesOrder> getAllSalesOrders() {
        return allSalesOrders;
    }

    // método main para executar o teste
    public static void main(String[] args) {
        TestArrayLists test = new TestArrayLists();
        test.createAndAddItemsToArrays();
        test.demonstrateArrayTraversal();
        System.out.println("ArrayLists criados nas classes modelo");
        System.out.println("Itens criados e adicionados aos arrays");
        System.out.println("Arrays percorridos e analisados");
    }
}