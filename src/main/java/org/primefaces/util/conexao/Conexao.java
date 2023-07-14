package org.primefaces.util.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.primefaces.util.base.AltException;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

public class Conexao {
	


	/**
	 * Usada em filtros
	 * 
	 * @param request HttpServletRequest
	 * @return java.sql.Connection
	 */
	public static Connection getConexao(HttpServletRequest request) {

		String dsName = "";
		try {

			dsName = (String) request.getSession().getAttribute("PUDATASOURCE");

		} catch (Exception e) {
			new AltException("Erro no getConexao(): " + e.getLocalizedMessage());
		}

		Connection conexao = null;
		DataSource ds = null;

		try {

			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup(dsName);

		} catch (NamingException ex) {
			ex.printStackTrace();
			new AltException("Erro no getConexao(): " + ex.getLocalizedMessage());
		}

		try {

			conexao = ds.getConnection();

		} catch (SQLException ex) {
			ex.printStackTrace();
			new AltException("Erro no getConexao(): " + ex.getLocalizedMessage());
		}

		return conexao;

	}

	public static Connection getConexao() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();

		String dsName = "";
		try {

			dsName = (String) request.getSession().getAttribute("PUDATASOURCE");

		} catch (Exception e) {
			new AltException("Erro no getConexao(): " + e.getLocalizedMessage());
		}

		Connection conexao = null;
		DataSource ds = null;

		try {

			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup(dsName);

		} catch (NamingException ex) {
			ex.printStackTrace();
			new AltException("Erro no getConexao(): " + ex.getLocalizedMessage());
		}

		try {

			conexao = ds.getConnection();

		} catch (SQLException ex) {
			ex.printStackTrace();
			new AltException("Erro no getConexao(): " + ex.getLocalizedMessage());
		}

		return conexao;

	}

	public static Statement getBanco(Connection conexao) throws Exception {
		Statement banco = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		return banco;
	}

	public static Connection getConexaoSemAutoCommit() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();

		String dsName = "";
		try {

			dsName = (String) request.getSession().getAttribute("PUDATASOURCE");

		} catch (Exception e) {
			new AltException("Erro no getConexao(): " + e.getLocalizedMessage());
		}

		Connection conexao = null;
		DataSource ds = null;

		try {

			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup(dsName);

		} catch (NamingException ex) {
			ex.printStackTrace();
			new AltException("Erro no getConexao(): " + ex.getLocalizedMessage());
		}

		try {

			conexao = ds.getConnection();

			conexao.setAutoCommit(false);

		} catch (SQLException ex) {
			ex.printStackTrace();
			new AltException("Erro no getConexao(): " + ex.getLocalizedMessage());
		}

		return conexao;

	}

	/*
	 * Conexão Usada para Importação de Dados
	 */
	public static Connection getConexaoImp(String servidor, String porta, String db) throws Exception {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();

		String user = "";
		String senha = "";

		try {

			user = (String) request.getSession().getAttribute("PUUSUARIO");
			senha = (String) request.getSession().getAttribute("PUSENHA");

		} catch (Exception e) {
			new AltException("Erro no getConexaoImp(): " + e.getLocalizedMessage());
		}

		return getConexaoImp(servidor, porta, db, user, senha);

	}

	/*
	 * Conexão Usada para Importação de Dados
	 */
	public static Connection getConexaoImp(String servidor, String porta, String db, String user, String senha) throws Exception {

		servidor = servidor == null || servidor.trim().equals("") ? "localhost" : servidor;
		porta = porta == null || porta.trim().equals("") ? "5432" : porta;
		user = user == null || user.trim().length() <= 0 ? "postgres" : user;
		senha = senha == null || senha.trim().length() <= 0 ? "427623" : senha;

		Connection conexao;

		Class.forName("org.postgresql.Driver").newInstance();

		String url = "jdbc:postgresql://" + servidor + ":" + porta + "/" + db;

		conexao = DriverManager.getConnection(url, user, senha);

		conexao.setAutoCommit(false);

		return conexao;

	}
}
