package org.primefaces.apollo.dao.usuarios;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

import org.primefaces.apollo.entidades.usuarios.Usuario;

public class UsuarioDAO {

    private EntityManager em;

    @Inject
    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void inserir(Usuario usuario) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(usuario);
        em.getTransaction().commit();
    }


    public void atualizar(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void remover(Usuario usuario) {
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Usuario buscarPorId(int idUsuario) {
        return em.find(Usuario.class, idUsuario);
    }

    public Usuario buscarPorCpf(String cpf) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf", Usuario.class);
        query.setParameter("cpf", cpf);
        try {
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    public List<Usuario> listarTodos() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
}
