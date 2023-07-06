package org.primefaces.apollo.entidades.cadastroProdutos.cadastroDeProdutos;

import java.util.Objects;

import org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos.Categoria;
import org.primefaces.apollo.entidades.superClasse.EntityGeneric;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * @author Thaison Lopes
 *
 */

@Entity
@Table(name = "cadastro_produtos")
public class Produto extends EntityGeneric {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cadastro_produtos")
	private long id_cadastro_produtos;

	@NotNull
	@Column(name = "codigo")
	private long codigo;

	@Column(name = "cod_barra")
	private String cod_barra;

	@NotNull
	@Column(name = "nome_do_produto", unique = true)
	private String nome_do_produto;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "modelo_marca")
	private String modelo_marca;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria id_categoria;

	@Column(name = "unidade")
	private String unidade;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "preco_compra")
	private Double preco_compra;

	@NotNull
	@Column(name = "preco_venda")
	private Double preco_venda;

	// Construtores

	public Produto() {
	}

	public Produto(String cod_barra, String nome_do_produto, Integer quantidade, String modelo_marca,
			Categoria id_categoria, String unidade, String observacao, Double preco_compra,
			Double preco_venda) {
		this.cod_barra = cod_barra;
		this.nome_do_produto = nome_do_produto;
		this.quantidade = quantidade;
		this.modelo_marca = modelo_marca;
		this.id_categoria = id_categoria;
		this.unidade = unidade;
		this.observacao = observacao;
		this.preco_compra = preco_compra;
		this.preco_venda = preco_venda;
	}

	// Getters e Setters

	public long getId_cadastro_produtos() {
		return id_cadastro_produtos;
	}

	public void setId_cadastro_produtos(long id_cadastro_produtos) {
		this.id_cadastro_produtos = id_cadastro_produtos;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getCod_barra() {
		return cod_barra;
	}

	public void setCod_barra(String cod_barra) {
		this.cod_barra = cod_barra;
	}

	public String getNome_do_produto() {
		return nome_do_produto;
	}

	public void setNome_do_produto(String nome_do_produto) {
		this.nome_do_produto = nome_do_produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getModelo_marca() {
		return modelo_marca;
	}

	public void setModelo_marca(String modelo_marca) {
		this.modelo_marca = modelo_marca;
	}

	public Categoria getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Categoria id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getPreco_compra() {
		return preco_compra;
	}

	public void setPreco_compra(Double preco_compra) {
		this.preco_compra = preco_compra;
	}

	public Double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(Double preco_venda) {
		this.preco_venda = preco_venda;
	}

	// hashCode e equals

	@Override
	public int hashCode() {
		return Objects.hash(id_cadastro_produtos, codigo, nome_do_produto);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Produto that = (Produto) o;
		return id_cadastro_produtos == that.id_cadastro_produtos
				&& codigo == that.codigo
				&& Objects.equals(nome_do_produto, that.nome_do_produto);
	}
}
