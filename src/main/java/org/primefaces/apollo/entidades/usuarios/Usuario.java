package org.primefaces.apollo.entidades.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

import org.primefaces.apollo.entidades.superClasse.EntityGeneric;

@Entity
public class Usuario extends EntityGeneric {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private Integer tipoUsuario;

	@Column(nullable = false)
	private Integer empresa;

	// Construtores, Getters e Setters

	public Usuario() {
	}

	public Usuario(String cpf, String login, String senha, Integer tipoUsuario, Integer empresa) {
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.empresa = empresa;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Usuario usuario = (Usuario) obj;
		return Objects.equals(idUsuario, usuario.idUsuario);
	}

	@Override
	public String toString() {
		return "Usuario{" + "idUsuario=" + idUsuario + ", cpf='" + cpf + '\'' + ", login='" + login + '\'' + ", senha='"
				+ senha + '\'' + ", tipoUsuario=" + tipoUsuario + ", empresa=" + empresa + '}';
	}
}
