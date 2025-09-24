import java.util.*;

// Interface base
interface Identificavel<ID> {
    ID getId();
}

// Exceção personalizada
class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}

// Repositório genérico
interface IRepository<T extends Identificavel<ID>, ID> {
    void salvar(T entidade);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void remover(ID id) throws EntidadeNaoEncontradaException;
}

// Implementação em memória
class InMemoryRepository<T extends Identificavel<ID>, ID> implements IRepository<T, ID> {
    private final Map<ID, T> storage = new HashMap<>();

    @Override
    public void salvar(T entidade) {
        storage.put(entidade.getId(), entidade);
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(storage.values()));
    }

    @Override
    public void remover(ID id) {
        if (!storage.containsKey(id)) {
            throw new EntidadeNaoEncontradaException("ID não encontrado: " + id);
        }
        storage.remove(id);
    }
}

// Exemplo de entidade Produto
class ProdutoRepo implements Identificavel<Integer> {
    private final Integer id;
    private final String nome;

    public ProdutoRepo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}

public class Exercicio7 {
    public static void main(String[] args) {
        IRepository<ProdutoRepo, Integer> repo = new InMemoryRepository<>();

        ProdutoRepo p1 = new ProdutoRepo(1, "Camiseta");
        ProdutoRepo p2 = new ProdutoRepo(2, "Calça");

        repo.salvar(p1);
        repo.salvar(p2);

        System.out.println("Todos produtos: " + repo.listarTodos());
        System.out.println("Buscar ID 1: " + repo.buscarPorId(1).orElse(null));

        repo.remover(1);
        System.out.println("Após remover ID 1: " + repo.listarTodos());

        try {
            repo.remover(99);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
