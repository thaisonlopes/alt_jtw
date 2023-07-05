package org.primefaces.util.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class GenericDAO<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	private Class<T> entityType;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	// Método para encontrar por ID
	public T encontrarPorId(Long id) {
		return entityManager.find(entityType, id);
	}

	// Método para salvar uma entidade
	public void salvar(T entidade) {
		entityManager.persist(entidade);
	}

	// Método para atualizar uma entidade
	public void atualizar(T entidade) {
		entityManager.merge(entidade);
	}

	// Método para excluir uma entidade
	public void excluir(T entidade) {
		entityManager.remove(entidade);
	}
}
