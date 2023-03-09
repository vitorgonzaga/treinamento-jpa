package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        executaCadastroInicial();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        // Buscando pelo Id
        Long id = 1l;
        Produto p = produtoDao.buscarPorId(id);
        System.out.println("Busca produto por Id: " + p.getNome() + " - " + p.getPreco());

        // Buscando todos os produtos
        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(prod -> System.out.println("Busca todos os produtos: " + prod.getNome() + " - " + prod.getPreco()));

        // Buscando por nome
        List<Produto> celularesXiaomi =  produtoDao.buscarPorNome("Xiaomi Redmi");
        celularesXiaomi.forEach(prod -> System.out.println("Busca por nome: " + prod.getNome() + " - " + prod.getPreco()));

        // Buscando por nome da Categoria
        List<Produto> celulares =  produtoDao.buscarProdutoPorNomeDaCategoria("CELULARES");
        celulares.forEach(prod -> System.out.println("Busca por nome da Categoria: " + prod.getNome() + " - " + prod.getPreco()));

        // Buscando apenas o preco do produto pelo seu nome
        BigDecimal precoDoXiaomi =  produtoDao.buscarPrecoDoProdutoPeloNomeDoProduto("Xiaomi Redmi");
        System.out.println("Busca do preco pelo nome do Produto: " + precoDoXiaomi);

        // Selecionando nome e preco do produto pelo seu nome
        List<Produto> nomePrecoProdutoPeloNome =  produtoDao.buscarNomePrecoDoProdutoPeloNomeDoProduto("Xiaomi Redmi");
        nomePrecoProdutoPeloNome.forEach(prod -> System.out.println("Selecionando nome e preco: " + prod.getNome() + " - " + prod.getPreco()));
    }

    private static void executaCadastroInicial() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);


        // Trecho de código abaixo foi isolado numa classe utilitária.
        // Considera-se o EntityManagerFactory como um atributo estatico da classe e
        // cria-se um método que utilizando-se desse atributo chama o método 'createEntityManager' retornando assim um EntityManager

        // EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja"); // virou atributo estático
        // EntityManager em = factory.createEntityManager(); // passou a ser retornado no método da classe utilitária

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        // É necessário iniciar uma transação
        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        // em.persist(celular); // passa a ser utilizado isoladamente dentro da classe produtoDao
        produtoDao.cadastrar(celular);
        em.getTransaction().commit(); // indispensável para a persistência no banco de dados
        em.close();
    }


}
