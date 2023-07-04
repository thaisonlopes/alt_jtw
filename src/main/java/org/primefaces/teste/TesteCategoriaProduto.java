package org.primefaces.teste;

import java.sql.Timestamp;
import java.util.Set;

import org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos.CategoriaProdutos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TesteCategoriaProduto {
    public static void main(String[] args) {
        // Configuração do EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ALT");

        // Criação do EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Início da transação
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            CategoriaProdutos categoria = new CategoriaProdutos();
            categoria.setDthr_create(Timestamp.valueOf("2023-07-04 00:32:00"));
            categoria.setDthr_update(Timestamp.valueOf("2023-07-04 00:33:00"));
            categoria.setCod_usuario_create(12);
            categoria.setCodigo(1L);
            categoria.setDescricao("Categoria Produto 1");

            // Validação da entidade antes de persistir
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            // Realiza a validação e obtém as violações, se houverem
            Set<ConstraintViolation<CategoriaProdutos>> violations = validator.validate(categoria);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<CategoriaProdutos> violation : violations) {
                    System.out.println(violation.getMessage());
                }
                throw new RuntimeException("A entidade não é válida.");
            }

            // Persiste a entidade
            entityManager.persist(categoria);

            // Commit da transação
            transaction.commit();
            System.out.println("Inserção concluída com sucesso!");
        } catch (Exception e) {
            // Rollback da transação em caso de erro
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fechamento do EntityManager
            entityManager.close();
        }

        // Fechamento do EntityManagerFactory
        entityManagerFactory.close();
    }
}
