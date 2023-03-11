package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        JPAUtil.executaCadastroInicial();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        // Buscando pelo Id
        Long id = 1L;
        Produto p = produtoDao.buscarPorId(id);
        System.out.println("Busca produto por Id: " + p.getNome() + " - " + p.getPreco());

        // Buscando todos os produtos
        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(prod -> System.out.println("Busca todos os produtos: " + prod.getNome() + " - " + prod.getPreco()));

        // Buscando por nome
        List<Produto> celularesXiaomi = produtoDao.buscarPorNome("Xiaomi Redmi");
        celularesXiaomi.forEach(prod -> System.out.println("Busca por nome: " + prod.getNome() + " - " + prod.getPreco()));

        // Buscando por nome da Categoria
        List<Produto> celulares = produtoDao.buscarProdutoPorNomeDaCategoria("CELULARES");
        celulares.forEach(prod -> System.out.println("Busca por nome da Categoria: " + prod.getNome() + " - " + prod.getPreco()));

        // Buscando apenas o preco do produto pelo seu nome
        BigDecimal precoDoXiaomi = produtoDao.buscarPrecoDoProdutoPeloNomeDoProduto("Xiaomi Redmi");
        System.out.println("Busca do preco pelo nome do Produto: " + precoDoXiaomi);

        // Selecionando nome e preco do produto pelo seu nome
        List<Produto> nomePrecoProdutoPeloNome = produtoDao.buscarNomePrecoDoProdutoPeloNomeDoProduto("Xiaomi Redmi");
        nomePrecoProdutoPeloNome.forEach(prod -> System.out.println("Selecionando nome e preco: " + prod.getNome() + " - " + prod.getPreco()));

    }

}
