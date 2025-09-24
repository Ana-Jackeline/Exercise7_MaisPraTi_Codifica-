import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

enum Moeda {
    BRL, USD, EUR
}

final class Dinheiro {
    private final BigDecimal valor;
    private final Moeda moeda;

    public Dinheiro(BigDecimal valor, Moeda moeda) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }
        this.valor = valor.setScale(2, RoundingMode.HALF_EVEN);
        this.moeda = Objects.requireNonNull(moeda);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dinheiro)) return false;
        Dinheiro d = (Dinheiro) o;
        return valor.equals(d.valor) && moeda == d.moeda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, moeda);
    }

    @Override
    public String toString() {
        return moeda + " " + valor;
    }
}

class ProdutoCarrinho {
    private final String nome;
    private final Dinheiro preco;

    public ProdutoCarrinho(String nome, Dinheiro preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Dinheiro getPreco() {
        return preco;
    }
}

class ItemCarrinho {
    private final ProdutoCarrinho produto;
    private final int quantidade;

    public ItemCarrinho(ProdutoCarrinho produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoCarrinho getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Dinheiro getTotal() {
        return new Dinheiro(
            produto.getPreco().getValor().multiply(BigDecimal.valueOf(quantidade)),
            produto.getPreco().getMoeda()
        );
    }
}

final class Carrinho {
    private final List<ItemCarrinho> itens;

    public Carrinho(List<ItemCarrinho> itens) {
        this.itens = Collections.unmodifiableList(new ArrayList<>(itens));
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public Carrinho adicionar(ItemCarrinho novoItem) {
        List<ItemCarrinho> novaLista = new ArrayList<>(itens);
        novaLista.add(novoItem);
        return new Carrinho(novaLista);
    }

    public Carrinho remover(ItemCarrinho item) {
        List<ItemCarrinho> novaLista = new ArrayList<>(itens);
        novaLista.remove(item);
        return new Carrinho(novaLista);
    }

    public Carrinho aplicarCupom(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 30) {
            throw new IllegalArgumentException("Cupom inválido (máx 30%)");
        }
        List<ItemCarrinho> novaLista = new ArrayList<>();
        for (ItemCarrinho item : itens) {
            BigDecimal valorComDesconto = item.getProduto().getPreco().getValor()
                .multiply(BigDecimal.valueOf(1 - porcentagem / 100.0))
                .setScale(2, RoundingMode.HALF_EVEN);
            ProdutoCarrinho produtoComDesconto = new ProdutoCarrinho(
                item.getProduto().getNome(),
                new Dinheiro(valorComDesconto, item.getProduto().getPreco().getMoeda())
            );
            novaLista.add(new ItemCarrinho(produtoComDesconto, item.getQuantidade()));
        }
        return new Carrinho(novaLista);
    }

    public Dinheiro calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        Moeda moeda = Moeda.BRL;
        for (ItemCarrinho item : itens) {
            total = total.add(item.getTotal().getValor());
            moeda = item.getTotal().getMoeda();
        }
        return new Dinheiro(total, moeda);
    }
}

public class Exercicio6 {
    public static void main(String[] args) {
        ProdutoCarrinho p1 = new ProdutoCarrinho("Livro", new Dinheiro(new BigDecimal("50.00"), Moeda.BRL));
        ProdutoCarrinho p2 = new ProdutoCarrinho("Fone", new Dinheiro(new BigDecimal("100.00"), Moeda.BRL));

        Carrinho c1 = new Carrinho(new ArrayList<>());
        c1 = c1.adicionar(new ItemCarrinho(p1, 2));
        c1 = c1.adicionar(new ItemCarrinho(p2, 1));

        System.out.println("Total sem desconto: " + c1.calcularTotal());

        Carrinho c2 = c1.aplicarCupom(20);
        System.out.println("Total com 20% de desconto: " + c2.calcularTotal());
    }
}
