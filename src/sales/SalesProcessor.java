package sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import catalog.Product;
import people.Customer;
import people.Salesperson;
import financial.PaymentMethod;

public class SalesProcessor {

    // Atributos
    private double totalSalesValue;
    private int numberOfSales;
    private int totalItemsSold;
    private double averageSaleValue;
    private boolean processingActive;
    private String currentPaymentType;

    // Lista para armazenar as vendas processadas
    private List<SalesOrder> processedSales;

    // Construtor
    public SalesProcessor() {
        this.totalSalesValue = 0.0;
        this.numberOfSales = 0;
        this.totalItemsSold = 0;
        this.averageSaleValue = 0.0;
        this.processingActive = true;
        this.processedSales = new ArrayList<>();
    }

    // Método principal que executa o processo de vendas
    public void executeSalesProcess() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE PROCESSAMENTO DE VENDAS ===");

        // Laço WHILE - continuar processamento de vendas enquanto o usuário desejar
        while (processingActive) {
            System.out.println("\n--- Nova Venda ---");

            // Criar uma nova venda
            SalesOrder currentSale = new SalesOrder();
            currentSale.setOrderNumber(numberOfSales + 1);
            currentSale.setOrderDate(new Date());
            currentSale.setStatus("Em Processamento");

            // Simular produtos disponíveis
            Product[] availableProducts = createSampleProducts();

            System.out.println("Produtos disponíveis:");
            // Laço FOR - exibir produtos disponíveis
            for (int i = 0; i < availableProducts.length; i++) {
                System.out.printf("%d - %s (R$ %.2f)%n",
                        i + 1,
                        availableProducts[i].getName(),
                        availableProducts[i].getSalesPrice());
            }

            double saleTotal = 0.0;
            int itemsInThisSale = 0;

            // Laço FOR - permite a adição de até 5 itens por venda
            for (int item = 1; item <= 5; item++) {
                System.out.printf("\nItem %d - Digite o número do produto (0 para finalizar): ", item);
                int productChoice = scanner.nextInt();

                if (productChoice == 0) {
                    break;
                }

                if (productChoice >= 1 && productChoice <= availableProducts.length) {
                    System.out.print("Quantidade: ");
                    int quantity = scanner.nextInt();

                    Product selectedProduct = availableProducts[productChoice - 1];
                    double itemTotal = selectedProduct.getSalesPrice() * quantity;
                    saleTotal += itemTotal;
                    itemsInThisSale += quantity;

                    System.out.printf("Adicionado: %s x%d = R$ %.2f%n",
                            selectedProduct.getName(), quantity, itemTotal);
                } else {
                    System.out.println("Produto inválido!");
                    item--; // Não contar este item inválido
                }
            }

            if (saleTotal > 0) {
                // Menu de Escolha do método de pagamento
                System.out.println("\nEscolha o método de pagamento:");
                System.out.println("1 - Dinheiro (5% desconto)");
                System.out.println("2 - Cartão de Débito (2% desconto)");
                System.out.println("3 - Cartão de Crédito");
                System.out.println("4 - PIX (3% desconto)");

                System.out.print("Opção: ");
                int paymentOption = scanner.nextInt();

                double finalValue = saleTotal;

                // Laço SWITCH - Opções de pagamento
                switch (paymentOption) {
                    case 1:
                        currentPaymentType = "Dinheiro";
                        finalValue = saleTotal * 0.95; // 5% desconto
                        System.out.println("Desconto de 5% aplicado!");
                        break;

                    case 2:
                        currentPaymentType = "Cartão de Débito";
                        finalValue = saleTotal * 0.98; // 2% desconto
                        System.out.println("Desconto de 2% aplicado!");
                        break;

                    case 3:
                        currentPaymentType = "Cartão de Crédito";
                        finalValue = saleTotal; // Sem desconto
                        System.out.println("Pagamento no cartão de crédito.");
                        break;

                    case 4:
                        currentPaymentType = "PIX";
                        finalValue = saleTotal * 0.97; // 3% desconto
                        System.out.println("Desconto de 3% aplicado!");
                        break;

                    default:
                        currentPaymentType = "Não especificado";
                        System.out.println("Opção inválida. Processando sem desconto.");
                        break;
                }

                // Finalizar a venda
                currentSale.setTotalValue(finalValue);
                currentSale.setStatus("Finalizada");
                processedSales.add(currentSale);

                // Atualizar estatísticas
                numberOfSales++;
                totalSalesValue += finalValue;
                totalItemsSold += itemsInThisSale;
                averageSaleValue = totalSalesValue / numberOfSales;

                System.out.printf("\n✓ Venda finalizada! Valor total: R$ %.2f%n", finalValue);
                System.out.printf("Método de pagamento: %s%n", currentPaymentType);
            } else {
                System.out.println("Nenhum item foi adicionado à venda.");
            }

            System.out.print("\nDeseja processar outra venda? (1-Sim / 0-Não): ");
            int continuar = scanner.nextInt();
            if (continuar != 1) {
                processingActive = false;
            }
        }

        // Exibindo relatório final
        displaySalesReport();
        scanner.close();
    }

    // Criar produtos
    private Product[] createSampleProducts() {
        Product[] products = new Product[4];

        products[0] = new Product();
        products[0].setName("Notebook Dell");
        products[0].setSalesPrice(2500.00);

        products[1] = new Product();
        products[1].setName("Mouse Wireless");
        products[1].setSalesPrice(85.00);

        products[2] = new Product();
        products[2].setName("Teclado Mecânico");
        products[2].setSalesPrice(350.00);

        products[3] = new Product();
        products[3].setName("Monitor 24\"");
        products[3].setSalesPrice(800.00);

        return products;
    }

    // Método para exibir relatório
    public void displaySalesReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           RELATÓRIO DE VENDAS");
        System.out.println("=".repeat(50));
        System.out.printf("Total de vendas processadas: %d%n", numberOfSales);
        System.out.printf("Valor total vendido: R$ %.2f%n", totalSalesValue);
        System.out.printf("Total de itens vendidos: %d%n", totalItemsSold);
        System.out.printf("Valor médio por venda: R$ %.2f%n", averageSaleValue);
        System.out.println("=".repeat(50));
    }

    // Getters
    public double getTotalSalesValue() {
        return totalSalesValue;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public int getTotalItemsSold() {
        return totalItemsSold;
    }

    public double getAverageSaleValue() {
        return averageSaleValue;
    }

    public boolean isProcessingActive() {
        return processingActive;
    }

    public String getCurrentPaymentType() {
        return currentPaymentType;
    }

    public List<SalesOrder> getProcessedSales() {
        return processedSales;
    }

    // Criei este método teste
    public static void main(String[] args) {
        SalesProcessor processor = new SalesProcessor();
        processor.executeSalesProcess();
    }
}