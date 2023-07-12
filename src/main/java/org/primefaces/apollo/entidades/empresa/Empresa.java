package org.primefaces.apollo.entidades.empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

import org.primefaces.apollo.entidades.superClasse.EntityGeneric;

@Entity
@Table(name = "empresa")
public class Empresa extends EntityGeneric {

	private static final long serialVersionUID = 9203518375554698874L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String nome;

    @Column(name = "tipo_logradouro")
    private String tipo_logradouro;


	@Column(name = "codigo", nullable = false)
	private long codigo;

    private String bairro;

    private String logradouro;

    private String numero;

    private String municipio;

    private String uf;

    private String telefone;

    @Column(name = "nome_contato")
    private String nome_contato;

    @Column(name = "telefone_contato")
    private String telefone_contato;

    public Empresa() {
    }

    public Empresa(Long codigo,String cnpj, String nome, String tipo_logradouro, String bairro, String logradouro, String numero,
            String municipio, String uf, String telefone, String nome_contato, String telefone_contato) {
        this.codigo = codigo;
    	this.cnpj = cnpj;
        this.nome = nome;
        this.tipo_logradouro = tipo_logradouro;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.municipio = municipio;
        this.uf = uf;
        this.telefone = telefone;
        this.nome_contato = nome_contato;
        this.telefone_contato = telefone_contato;
    }
    
    public String getTipo_logradouro() {
		return tipo_logradouro;
	}

	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoLogradouro() {
        return tipo_logradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipo_logradouro = tipoLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome_contato() {
        return nome_contato;
    }

    public void setNome_contato(String nome_contato) {
        this.nome_contato = nome_contato;
    }

    public String getTelefone_contato() {
        return telefone_contato;
    }

    public void setTelefone_contato(String telefone_contato) {
        this.telefone_contato = telefone_contato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return cnpj.equals(empresa.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo_logradouro='" + tipo_logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", municipio='" + municipio + '\'' +
                ", uf='" + uf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nome_contato='" + nome_contato + '\'' +
                ", telefone_contato='" + telefone_contato + '\'' +
                '}';
    }
}
