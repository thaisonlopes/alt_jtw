package org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos;

import java.util.Objects;

import org.primefaces.apollo.entidades.superClasse.EntityGeneric;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * @author Thaison Lopes
 *
 */

@Entity
@Table(name = "categoria_produtos")
public class CategoriaProdutos extends EntityGeneric {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idCategoria;

	@Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@Column(name = "descricao", unique = true, nullable = false)
	private String descricao;

	public CategoriaProdutos() {
	}

	public CategoriaProdutos(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaProdutos(long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

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
		return Objects.equals(idCategoria, other.idCategoria);
	}

	@Override
	public String toString() {
		return "CategoriaProdutos [idCategoria=" + idCategoria + ", codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}

}