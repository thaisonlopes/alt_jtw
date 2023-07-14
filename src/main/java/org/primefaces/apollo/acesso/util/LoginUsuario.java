package org.primefaces.apollo.acesso.util;

import java.io.Serializable;

import org.primefaces.apollo.entidades.empresa.Empresa;
import org.primefaces.apollo.entidades.usuarios.usuario.Usuario;

public class LoginUsuario implements Serializable {

	private static final long serialVersionUID = -3854760414532198046L;

	private Usuario usuario;
	private Empresa empresa;
	private boolean logado = false;
	private String subDominio;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	public String getSubDominio() {
		return subDominio;
	}
	public void setSubDominio(String subDominio) {
		this.subDominio = subDominio;
	}

	

}
