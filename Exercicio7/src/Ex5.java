import java.math.BigDecimal;

abstract class FormaPagamento {
    public abstract void validarPagamento(String identificador) throws PagamentoInvalidoException;
    public abstract void processarPagamento(BigDecimal valor);
}

class PagamentoInvalidoException extends RuntimeException {
    public PagamentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

class CartaoCredito extends FormaPagamento {
    @Override
    public void validarPagamento(String numeroCartao) {
        if (numeroCartao == null || !numeroCartao.matches("\\d{16}")) {
            throw new PagamentoInvalidoException("Número de cartão inválido");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com Cartão de Crédito.");
    }
}

class Boleto extends FormaPagamento {
    @Override
    public void validarPagamento(String codigoBoleto) {
        if (codigoBoleto == null || !codigoBoleto.matches("\\d{47}")) {
            throw new PagamentoInvalidoException("Código de boleto inválido");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com Boleto.");
    }
}

class Pix extends FormaPagamento {
    @Override
    public void validarPagamento(String chavePix) {
        if (chavePix == null || chavePix.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Chave Pix inválida");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        System.out.println("Pagamento de R$" + valor + " realizado via Pix.");
    }
}

public class Exercicio5 {
    public static void main(String[] args) {
        FormaPagamento f1 = new CartaoCredito();
        FormaPagamento f2 = new Boleto();
        FormaPagamento f3 = new Pix();

        try {
            f1.validarPagamento("1234567812345678");
            f1.processarPagamento(new BigDecimal("250.50"));

            f2.validarPagamento("12345678901234567890123456789012345678901234567");
            f2.processarPagamento(new BigDecimal("100.00"));

            f3.validarPagamento("ana@banco.com");
            f3.processarPagamento(new BigDecimal("75.25"));

            // Exemplo inválido
            f1.validarPagamento("111");
        } catch (PagamentoInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
