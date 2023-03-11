package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteCriteria {

    public static void main(String[] args) {

        JPAUtil.executaCadastroInicial();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        produtoDao.buscarPorParametrosUsingCriteriaAPI("PS5", null, null);
        produtoDao.buscarPorParametrosUsingCriteriaAPI(null, null, LocalDate.now());
        produtoDao.buscarPorParametrosUsingCriteriaAPI("PS5", new BigDecimal(200), null);

    }
}
