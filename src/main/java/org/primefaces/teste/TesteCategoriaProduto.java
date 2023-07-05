package org.primefaces.teste;

import org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos.CategoriaProdutos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TesteCategoriaProduto {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ALT");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            CategoriaProdutos categoriaProduto = new CategoriaProdutos();
            categoriaProduto.setCodigo(1L);
            categoriaProduto.setDescricao("Categoria De Teste");

            em.persist(categoriaProduto);
            transaction.commit();

            System.out.println("Registro inserido com sucesso!");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao inserir o registro: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
