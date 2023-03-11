package br.com.alura.loja.testes;

import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class PerformanceConsultas {

    public static void main(String[] args) {

        JPAUtil.executaCadastroInicial();

        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);

        em.close();

        System.out.println(pedido.getData()); // normal, sem impacto do fetchType Lazy
        System.out.println(pedido.getCliente().getNome()); // erro! Impacto do fetchType Lazy. Precisa planejar a query na classe DAO.

    }
}
