package org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "categoria_produtos")
public class CategoriaProdutos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private long idCategoria;

	@Column(name = "dthr_create")
	private Timestamp dthr_create;

	@Column(name = "cod_usuario_create")
	private int cod_usuario_create;

	@Column(name = "dthr_update")
	private Timestamp dthr_update;

	@Column(name = "cod_usuario_update")
	private int cod_usuario_update;

	@Column(name = "codigo", unique = true, nullable = false)
	private long codigo;

	@Size(min = 1, max = 255)
	@Column(name = "descricao", unique = true, nullable = false)
	private String descricao;

	// Construtores

	public CategoriaProdutos() {
	}

	public CategoriaProdutos(long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	// Getters e Setters

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Timestamp getDthr_create() {
		return dthr_create;
	}

	public void setDthr_create(Timestamp dthr_create) {
		this.dthr_create = dthr_create;
	}

	public int getCod_usuario_create() {
		return cod_usuario_create;
	}

	public void setCod_usuario_create(int cod_usuario_create) {
		this.cod_usuario_create = cod_usuario_create;
	}

	public Timestamp getDthr_update() {
		return dthr_update;
	}

	public void setDthr_update(Timestamp dthr_update) {
		this.dthr_update = dthr_update;
	}

	public int getCod_usuario_update() {
		return cod_usuario_update;
	}

	public void setCod_usuario_update(int cod_usuario_update) {
		this.cod_usuario_update = cod_usuario_update;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// hashCode e equals

	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaProdutos other = (CategoriaProdutos) obj;
		return idCategoria == other.idCategoria;
	}

	@Override
	public String toString() {
		return "CategoriaProdutos [idCategoria=" + idCategoria + ", dthr_create=" + dthr_create
				+ ", cod_usuario_create=" + cod_usuario_create + ", dthr_update=" + dthr_update
				+ ", cod_usuario_update=" + cod_usuario_update + ", codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}

}
