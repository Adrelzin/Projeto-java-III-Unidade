
public class Pix extends Pagamento {
    private String chavePix;

    @Override
    public void processarPagamento() {
        
        System.out.println("Processando pagamento via Pix para chave: " + chavePix);
    }

    
}
