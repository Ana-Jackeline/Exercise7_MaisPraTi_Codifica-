public class ProdutoComDesconto extends Produto {

    public ProdutoComDesconto(String nome, double preco, int quantidadeEmEstoque) {
        super(nome, preco, quantidadeEmEstoque);
    }

    public void aplicarDesconto(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 50) {
            throw new IllegalArgumentException("Desconto deve estar entre 0% e 50%");
        }
        double novoPreco = getPreco() * (1 - porcentagem / 100.0);
        setPreco(novoPreco);
    }

    public static void main(String[] args) {
        ProdutoComDesconto p2 = new ProdutoComDesconto("Smartphone", 2000.0, 5);
        System.out.println("Preço original: " + p2.getPreco());

        p2.aplicarDesconto(20);
        System.out.println("Após 20% de desconto: " + p2.getPreco());

        try {
            p2.aplicarDesconto(70);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
