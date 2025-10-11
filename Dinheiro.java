
public class Dinheiro extends Pagamento {
    private double troco;

    @Override
    public void processarPagamento() {
        
        System.out.println("Processando pagamento em dinheiro. Troco: " + troco);
    }

    
}
