package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), Categoria.CELULARES);

        // Trecho de código abaixo foi isolado numa classe utilitária.
        // Considera-se o EntityManagerFactory como um atributo estatico da classe e
        // cria-se um método que utilizando-se desse atributo chama o método 'createEntityManager' retornando assim um EntityManager

        // EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja"); // virou atributo estático
        // EntityManager em = factory.createEntityManager(); // passou a ser retornado no método da classe utilitária

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);

        // É necessário iniciar uma transação
        em.getTransaction().begin();
        // em.persist(celular); // passa a ser utilizado isoladamente dentro da classe dao
        dao.cadastrar(celular);
        em.getTransaction().commit(); // indispensável para a persistência no banco de dados
        em.close();

    }


}
