package org.primefaces.teste;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.primefaces.apollo.entidades.empresa.Empresa;
import org.primefaces.apollo.entidades.usuarios.tipoUsuario.TipoUsuario;
import org.primefaces.apollo.entidades.usuarios.usuario.Usuario;

public class UsuarioTeste {

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

            // Criar a instância da Empresa
            Empresa empresa = new Empresa(1L,"38115362000109","ALT", "Rua", "Eva Mendes", "147", "Turmalina", "Pau D'óleo", "MG", "38991749832", "Thaison Lopes", "38991749832");

            // Criar a instância do Usuario
            Usuario usuario = new Usuario(1L, "12345678901", "senha123", tipoUsuario, empresa, null);

            // Persistir o Usuario no banco de dados
            entityManager.persist(usuario);

            // Commit da transação
            transaction.commit();

            System.out.println("Usuário inserido com sucesso!");
        } catch (Exception e) {
            // Rollback em caso de erro
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao inserir o Usuário: " + e.getMessage());
        } finally {
            // Fechar o EntityManager
            entityManager.close();

            // Fechar o EntityManagerFactory
            entityManagerFactory.close();
        }
    }
}
