package org.primefaces.apollo.bean.cadastroProdutos.categoriaProdutos;

import org.primefaces.apollo.dao.cadastroProdutos.categoriaProdutos.CategoriaDAO;
import org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos.Categoria;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("categoriaBean")
@ViewScoped
public class CategoriaBean implements java.io.Serializable {

	private static final String tela = "Cadastro De Categoria";

	private static final long serialVersionUID = 4232466334871258014L;

	@Inject
	private CategoriaDAO dao;

	private Categoria ent;

	private long codigo;

	@PostConstruct
	private void init() {
		InserirComoTeste();
	}

	public static String getTela() {
		return tela;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	private void InserirComoTeste() {

		try {
			ent = dao.getPorCodigo(1);

			if (ent == null) {
				ent = new Categoria();
				ent.setCodigo(1l);
				ent.setDescricao("Categoria De Teste");
				dao.salvar(tela, ent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
