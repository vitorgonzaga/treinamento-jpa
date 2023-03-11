package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private final EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        produto = this.em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id); // the find is specific method to get one entity
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto AS p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto AS p WHERE p.nome = :nome"; // using double point before each parameter
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome) // double point doesn't be part of parameter here
                .getResultList();
    }

    public List<Produto> buscarProdutoPorNomeDaCategoria(String categoria) {
        String jpql = "SELECT p FROM Produto AS p WHERE p.categoria.nome = :categoria"; // using double point before each parameter
        // ":categoria" -> named parameter;
        // could be also "?1", ?2, ?3, ?4 ...
        return em.createQuery(jpql, Produto.class)
                .setParameter("categoria", categoria) // double point doesn't be part of parameter here
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPeloNomeDoProduto(String nomeDoProduto) {
        String jpql = "SELECT p.preco FROM Produto AS p WHERE p.nome = :nome"; // using double point before each parameter
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nomeDoProduto) // double point doesn't be part of parameter here
                .getSingleResult();
    }

    public List<Produto> buscarNomePrecoDoProdutoPeloNomeDoProduto(String nome) {
        String jpql = "SELECT p.nome, p.preco FROM Produto AS p WHERE p.nome = :nome"; // using double point before each parameter
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome) // double point doesn't be part of parameter here
                .getResultList();
    }

    public List<Produto> buscarPorParametrosUsingJPQL(String nome, BigDecimal preco, LocalDate dataCadastro) {
        String jpql = "SELECT p from Produto p WHERE 1=1";
        if (nome != null && !nome.trim().isEmpty()) {
            jpql = "WHERE p.nome = :nome ";
        }
        if (preco != null) {
            jpql = "AND p.preco = :preco ";
        }
        if (dataCadastro != null) {
            jpql = "AND p.dataCadastro = :dataCadastro ";
        }
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {
            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }
        return query.getResultList();
    }

    public List<Produto> buscarPorParametrosUsingCriteriaAPI(String nome, BigDecimal preco, LocalDate dataCadastro) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);

        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = criteriaBuilder.and();

        if (nome != null && !nome.trim().isEmpty()) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);
        return em.createQuery(query).getResultList();
    }
}
