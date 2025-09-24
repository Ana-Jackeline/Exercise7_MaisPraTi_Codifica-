import java.math.BigDecimal;

// Estratégia de frete
interface CalculadoraFrete {
    BigDecimal calcular(Pedido pedido);
}

// Entidade Pedido
class Pedido {
    private final String cep;
    private final BigDecimal valor;
    private CalculadoraFrete estrategiaFrete;

    public Pedido(String cep, BigDecimal valor, CalculadoraFrete estrategiaFrete) {
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido");
        }
        this.cep = cep;
        this.valor = valor;
        this.estrategiaFrete = estrategiaFrete;
    }

    public String getCep() {
        return cep;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setEstrategiaFrete(CalculadoraFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public BigDecimal calcularFrete() {
        return estrategiaFrete.calcular(this);
    }
}

// Estratégias concretas
class Sedex implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        return new BigDecimal("20.00");
    }
}

class Pac implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        return new BigDecimal("10.00");
    }
}

class RetiradaNaLoja implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        return BigDecimal.ZERO;
    }
}

public class Exercicio8 {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("12345678", new BigDecimal("150.00"), new Sedex());
        System.out.println("Frete Sedex: R$" + pedido.calcularFrete());

        pedido.setEstrategiaFrete(new Pac());
        System.out.println("Frete PAC: R$" + pedido.calcularFrete());

        pedido.setEstrategiaFrete(new RetiradaNaLoja());
        System.out.println("Retirada na loja: R$" + pedido.calcularFrete());

        // Estratégia promocional via lambda (frete grátis acima de 100)
        pedido.setEstrategiaFrete(p -> p.getValor().compareTo(new BigDecimal("100")) > 0 ? BigDecimal.ZERO : new BigDecimal("15.00"));
        System.out.println("Frete promocional: R$" + pedido.calcularFrete());
    }
}
