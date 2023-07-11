package org.primefaces.teste;

import java.time.LocalDateTime;

import org.primefaces.apollo.entidades.log.Log;
import org.primefaces.apollo.entidades.usuarios.usuario.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TesteLog {
    public static void main(String[] args) {
        // Obtém o EntityManager a partir da unidade de persistência "ALT" definida no arquivo persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ALT");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Cria uma instância de Log
            Log log = new Log();
            log.setUsuario(em.find(Usuario.class, 1)); // Define o usuário com ID 1
            log.setHora(LocalDateTime.now());
            log.setAcao("Ação de teste");
            log.setTela("Tela de teste");
            log.setDescricao("Descrição de teste");
            log.setDados("Dados de teste");
            log.setIp("127.0.0.1");
            log.setDispositivo("Dispositivo de teste");

            // Persiste o objeto Log
            em.persist(log);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
