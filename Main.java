import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Cliente novoCliente = cadastrarCliente(scanner);
                    clientes.add(novoCliente);
                    System.out.println("\nCliente Cadastrado!\n");
                    novoCliente.exibirCliente();
                    break;

                case 2:
                    Produto novoProduto = cadastrarProduto(scanner);
                    produtos.add(novoProduto);
                    System.out.println("\nProduto Cadastrado!");
                    novoProduto.exibirProduto();
                    break;

                case 3:
                    if (clientes.isEmpty()) {
                        System.out.println("Não tem clientes cadastrados");
                        break;
                    }

                    if (produtos.isEmpty()) {
                        System.out.println("Não tem produtos cadastrados");
                        break;
                    }

                    Pedido novoPedido = fazerPedido(scanner, clientes, produtos, pedidos.size() + 1);
                    pedidos.add(novoPedido);
                    System.out.println("\nPedido realizado com sucesso!\n");
                    novoPedido.exibirPedido();
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção Inválida!");
            }

        } while (opcao != 4);

        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("""
                ========================================
                Bem-vindo ao Restaurante de Darel!
                ========================================
                1 - Cadastrar Cliente
                2 - Cadastrar Produto
                3 - Fazer Pedido
                4 - Sair
                ========================================
                """);
    }

    public static Cliente cadastrarCliente(Scanner scanner) {
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        return new Cliente(id, nome, telefone, email);
    }

    public static Produto cadastrarProduto(Scanner scanner) {
        System.out.print("Digite o id do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        return new Produto(id, nome, preco, categoria);
    }

    public static Pedido fazerPedido(Scanner scanner, ArrayList<Cliente> clientes, ArrayList<Produto> produtos,
            int idPedido) {
        System.out.println("\n=== Clientes Disponíveis ===");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.print((i + 1) + " - ");
            clientes.get(i).exibirCliente();
        }
        System.out.print("Escolha o cliente (ID): ");
        int indiceCliente = scanner.nextInt() - 1;
        scanner.nextLine();

        Cliente clienteSelecionado = clientes.get(indiceCliente);

        System.out.print("Digite o tipo do pedido (Delivery/Presencial): ");
        String tipoPedido = scanner.nextLine();

        ArrayList<ItemPedido> itens = new ArrayList<>();
        String continuar;

        do {
            ItemPedido item = cadastrarItemPedido(scanner, produtos);
            itens.add(item);

            System.out.print("Deseja adicionar mais itens? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        Pedido pedido = new Pedido(idPedido, tipoPedido, 0, clienteSelecionado, itens, null);
        double valorTotal = pedido.calcularValorTotal();

        System.out.println("\n=== Resumo do Pedido ===");
        System.out.printf("Valor Total: R$ %.2f\n", valorTotal);

        Pagamento pagamento = escolherPagamento(scanner, valorTotal);

        pedido = new Pedido(idPedido, tipoPedido, valorTotal, clienteSelecionado, itens, pagamento);

        return pedido;
    }

    public static ItemPedido cadastrarItemPedido(Scanner scanner, ArrayList<Produto> produtos) {
        System.out.println("\n=== Produtos Disponíveis ===");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.print((i + 1) + " - ");
            produtos.get(i).exibirProduto();
        }

        System.out.print("Escolha o produto (número): ");
        int indiceProduto = scanner.nextInt() - 1;
        scanner.nextLine();

        Produto produtoSelecionado = produtos.get(indiceProduto);

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        return new ItemPedido(quantidade, 0, produtoSelecionado);
    }

    public static Pagamento escolherPagamento(Scanner scanner, double valorTotal) {
        System.out.println("\n=== Forma de Pagamento ===");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cartão");
        System.out.println("3 - Pix");
        System.out.print("Escolha a forma de pagamento: ");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();

        //ID do pagamento
        int idPagamento = (int) (System.currentTimeMillis() % 100000);

        switch (opcaoPagamento) {
            case 1:
                System.out.print("Digite o valor pago pelo cliente: R$ ");
                double valorPago = scanner.nextDouble();
                scanner.nextLine();
                double troco = valorPago - valorTotal;
                
                Dinheiro dinheiro = new Dinheiro(idPagamento, valorTotal, troco);
                dinheiro.processarPagamento();
                return dinheiro;

            case 2:
                System.out.print("Digite o número do cartão: ");
                String numeroCartao = scanner.nextLine();

                System.out.print("Digite o nome do titular: ");
                String nomeTitular = scanner.nextLine();

                System.out.print("Digite a validade (MM/AA): ");
                String validade = scanner.nextLine();

                return new Cartao(idPagamento, valorTotal, numeroCartao, nomeTitular, validade);

            case 3:
                System.out.print("Digite a chave Pix: ");
                String chavePix = scanner.nextLine();
                return new Pix(idPagamento, valorTotal, chavePix);

            default:
                System.out.println("Opção inválida, usando Dinheiro por padrão.");
                Dinheiro dinheiroPadrao = new Dinheiro(idPagamento, valorTotal, 0);
                dinheiroPadrao.processarPagamento();
                return dinheiroPadrao;
        }
    }
}
