import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    Cliente novoCliente = cadastrarCliente(scanner);
                    clientes.add(novoCliente);
                    System.out.println("\n Cliente Cadastrado com sucesso!\n");
                    novoCliente.exibirCliente();
                    break;

                case 2:
                    System.out.println("Cadastrar Produto (em desenvolvimento)");
                    break;

                case 3:
                    System.out.println("Cadastrar Pedido (em desenvolvimento)");
                    break;

                case 4:
                    System.out.println("Saindo");
                    break;

                default:
                    System.out.println("Opção Inválida!");
            }

        } while(opcao != 4); 
        
        scanner.close();
    }

    
    public static void exibirMenu() {
        System.out.println("""
                ========================================
                Bem-vindo ao Restaurante de Darel!
                ========================================
                1 - Cadastrar Cliente
                2 - Cadastrar Produto
                3 - Cadastrar Pedido
                4 - Sair
                ========================================
                """);
    }
    
    public static Cliente cadastrarCliente(Scanner scanner){
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
} 