package org.primefaces.teste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class TesteHibernate {
    public static void main(String[] args) {
        // Cria a fábrica de gerenciadores de entidades
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ALT");
        
        // Cria o gerenciador de entidades
        EntityManager em = emf.createEntityManager();
        
        // Cria um objeto Produto
        Produtos produtos = new Produtos();
        produtos.setNome("Celular");
        produtos.setPreco(999.99);
        
        // Inicia uma transação
        em.getTransaction().begin();
        
        // Persiste o objeto no banco de dados
        em.persist(produtos);
        
        // Comita a transação
        em.getTransaction().commit();
        
        // Fecha o gerenciador de entidades e a fábrica de gerenciadores de entidades
        em.close();
        emf.close();
        
        System.out.println("Produto persistido: " + produtos);
    }
}
