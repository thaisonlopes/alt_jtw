package org.primefaces.apollo.dao.cadastroProdutos.categoriaProdutos;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos.Categoria;
import org.primefaces.apollo.entidades.log.Log;
import org.primefaces.util.base.GenericDAO;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Stateless
public class CategoriaDAO extends GenericDAO {

	private static final long serialVersionUID = -245231862975645515L;

	@Override
	protected void setLog(Log log, Serializable pojo) {
		Categoria c = (Categoria) pojo;
		log.setC1(c.getCodigo() + "");
	}

	@Inject
	private EntityManager session;

	private EntityManager getSession() {
		session.joinTransaction();
		return session;
	}

	public Categoria salvar(String tela, Categoria l) {
		EntityManager session = getSession();
		try {
			setTela(tela);
			l = super.salvarOuAtualizar(session, l);
			commitSalva(l, session);
		} catch (Exception e) {
			rollback(e);

		}
		return l;
	}

	public void excluir(String tela, Categoria l) {
		EntityManager session = getSession();
		try {
			setTela(tela);
			super.excluir(l, session);
		} catch (Exception e) {
			rollback(e);

		}
	}

	public Categoria getPorCodigo(long codigo) {
		String sql = "SELECT e FROM Categoria e where e.codigo = ?1";
		return getPojoUnique(session, Categoria.class, sql, codigo);
	}

	public Categoria getPorId(long idCategoria) {
		return getPojo(session, Categoria.class, idCategoria);
	}

	public List<Categoria> getLista() {
		String sql = "SELECT e FROM Categoria e order by e.codigo";
		return Collections.unmodifiableList(getPureList(session, Categoria.class, sql));

	}

}
