package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    ;

    public Pedido getPedidoById(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        // A tipagem poderia ser um Array de object -> Object[], contudo a boa prática é criar uma classe
        // no caso, a RelatorioDeVendaVo (Vo -> Value Object)
        String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, SUM(item.quantidade), "
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY item.quantidade DESC";

        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

    // query planejada por causa da utilização do fetchType lazy no relacionamento 'toOne' com o cliente na entidade pedido
    // "JOIN FETCH" nesta consulta resulta em comportamento EAGER, ou seja, ele vai fazer o fetch do Cliente.
    public Pedido buscarPedidoComCliente(Long id) {
        return em.createQuery("SELECT p from Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
