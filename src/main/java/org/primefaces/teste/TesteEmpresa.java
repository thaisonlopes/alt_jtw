package org.primefaces.teste;

import org.primefaces.apollo.entidades.empresa.Empresa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TesteEmpresa {
    public static void main(String[] args) {
        // Obtém o EntityManager a partir da unidade de persistência "ALT" definida no arquivo persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ALT");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Cria uma instância de Empresa
            Empresa empresa = new Empresa();
            empresa.setCnpj("38115362000109");
            empresa.setNome("ALT");
            empresa.setTipoLogradouro("Rua");
            empresa.setLogradouro("Eva Mendes");
            empresa.setNumero("147");
            empresa.setMunicipio("Turmalina");
            empresa.setBairro("Pau D'óleo");
            empresa.setUf("MG");
            empresa.setTelefone("38991749832");
            empresa.setNome_contato("Thaison Lopes");
            empresa.setTelefone_contato("38991749832");

            // Persiste o objeto Empresa
            em.persist(empresa);

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
