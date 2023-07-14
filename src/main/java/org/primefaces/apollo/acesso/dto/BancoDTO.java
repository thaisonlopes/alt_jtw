package org.primefaces.apollo.acesso.dto;

import java.io.Serializable;

public class BancoDTO implements Serializable {

	private static final long serialVersionUID = -2281147897038470497L;

	private Integer codigo;
	private String nomeDB;
	private String dataSource;
	private String usuarioDB;
	private String senhaDB;
	private String urlDB;
	private String empresa;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeDB() {
		return nomeDB;
	}

	public void setNomeDB(String nomeDB) {
		this.nomeDB = nomeDB;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getUsuarioDB() {
		return usuarioDB;
	}

	public void setUsuarioDB(String usuarioDB) {
		this.usuarioDB = usuarioDB;
	}

	public String getSenhaDB() {
		if (senhaDB == null || senhaDB.length() < 1) {
			return "s1n73$&";
		}
		return senhaDB;
	}

	public void setSenhaDB(String senhaDB) {
		this.senhaDB = senhaDB;
	}

	public String getUrlDB() {
		return urlDB;
	}

	public void setUrlDB(String urlDB) {
		this.urlDB = urlDB;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	

}
