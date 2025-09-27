# 📘 Lista de Tarefas 7 -- Java OO

Este repositório contém a resolução da **Lista de Tarefas 7**, com 8 exercícios práticos.

------------------------------------------------------------------------

## 📂 Estrutura do Projeto

Cada exercício foi implementado em um **arquivo próprio**, com todas as
classes auxiliares incluídas dentro dele.\
No IntelliJ, basta abrir o projeto e rodar o `main` de cada arquivo
`ExercicioN.java`.

    src/
     ├── Exercicio1.java   # Encapsulamento (Produto)
     ├── Exercicio2.java   # Encapsulamento com desconto
     ├── Exercicio3.java   # Herança (Funcionários)
     ├── Exercicio4.java   # Polimorfismo (IMeioTransporte)
     ├── Exercicio5.java   # Abstração (Sistema de Pagamentos)
     ├── Exercicio6.java   # Imutabilidade (Carrinho de Compras)
     ├── Exercicio7.java   # Generics (Repositório Genérico)
     └── Exercicio8.java   # Strategy (Cálculo de Frete)

------------------------------------------------------------------------

## 🚀 Como Executar no IntelliJ IDEA

1.  Abra o IntelliJ e escolha **Open** → selecione a pasta do projeto.\
2.  Espere o IntelliJ indexar e compilar os arquivos automaticamente.\
3.  No painel lateral (`Project`), abra a pasta `src/`.\
4.  Clique com o botão direito em um arquivo, por exemplo
    `Exercicio3.java`.\
5.  Escolha **Run 'Exercicio3.main()'**.\
6.  O resultado será exibido no console inferior do IntelliJ.

⚡ Dica: Você pode criar **configurações de execução** para cada
`ExercicioN` no IntelliJ (Menu Run → Edit Configurations).

------------------------------------------------------------------------

## 📑 Descrição dos Exercícios

-   **Exercício 1 -- Encapsulamento (Produto)**\
    Classe `Produto` com atributos privados, getters/setters validados e
    exceções.

-   **Exercício 2 -- Encapsulamento com Desconto**\
    Extensão de `Produto` com método `aplicarDesconto`, validando
    valores entre **0% e 50%**.

-   **Exercício 3 -- Herança (Funcionários)**\
    Hierarquia `Funcionario`, `Gerente`, `Desenvolvedor` com sobrescrita
    de método `calcularBonus`.

-   **Exercício 4 -- Polimorfismo (IMeioTransporte)**\
    Interface `IMeioTransporte` implementada por `Carro`, `Bicicleta` e
    `Trem`.

-   **Exercício 5 -- Abstração (Sistema de Pagamentos)**\
    Classe abstrata `FormaPagamento` e subclasses `CartaoCredito`,
    `Boleto`, `Pix`.

-   **Exercício 6 -- Imutabilidade (Carrinho de Compras)**\
    Objeto de valor `Dinheiro` e carrinho imutável que retorna **novos
    objetos** em cada operação.

-   **Exercício 7 -- Generics (Repositório em Memória)**\
    Repositório genérico `InMemoryRepository` com `Map` e exceções
    personalizadas.

-   **Exercício 8 -- Strategy (Cálculo de Frete com Lambdas)**\
    Interface `CalculadoraFrete`, estratégias (`Sedex`, `Pac`,
    `RetiradaNaLoja`) e lambda promocional.

------------------------------------------------------------------------

## ✨ Autor

Implementado por **Ana Jackeline Alves da Silva**\
📚 Lista de exercícios de Programação Orientada a Objetos em Java.
