public class Produto {
    private int idProduto;
    private String nome;
    private double preco;
    private String categoria;

    public void exibirProduto() {
        System.out.println("Produto: " + nome + ", Categoria: " + categoria + ", Preço: " + preco);
    }


    public int getIdProduto() {
        return this.idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}