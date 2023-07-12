package org.primefaces.teste;

import org.primefaces.apollo.entidades.usuarios.tipoUsuario.TipoUsuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TipoUsuarioTeste {

    public static void main(String[] args) {
        // Criação do EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ALT");

        // Criação do EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Criação da transação
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Iniciar a transação
            transaction.begin();

            // Criar a instância do TipoUsuario
            TipoUsuario tipoUsuario = new TipoUsuario("1", "Administrador");

            // Persistir o TipoUsuario no banco de dados
            entityManager.persist(tipoUsuario);

            // Commit da transação
            transaction.commit();

            System.out.println("TipoUsuario inserido com sucesso!");
        } catch (Exception e) {
            // Rollback em caso de erro
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao inserir o TipoUsuario: " + e.getMessage());
        } finally {
            // Fechar o EntityManager
            entityManager.close();

            // Fechar o EntityManagerFactory
            entityManagerFactory.close();
        }
    }
}
