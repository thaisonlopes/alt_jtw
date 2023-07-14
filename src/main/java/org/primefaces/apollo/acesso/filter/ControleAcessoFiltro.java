package org.primefaces.apollo.acesso.filter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.primefaces.util.conexao.Conexao;

import jakarta.servlet.http.HttpServletRequest;

public class ControleAcessoFiltro {
	


	public boolean isAcessoModuloSistema(long idUsuarioEntidade, int coigoSistema, HttpServletRequest request) {

		boolean temAcesso = false;

		Connection conn = null;
		Statement stmt = null;

		try {

			conn = Conexao.getConexao(request);
			stmt = Conexao.getBanco(conn);

			try {

				StringBuilder sql = new StringBuilder();

				sql.append(" SELECT codpermissao, acesso FROM int_permissaosistema");
				sql.append(" WHERE usuarioentidade=").append(idUsuarioEntidade);
				sql.append(" AND codsistema=").append(coigoSistema);

				ResultSet rs = stmt.executeQuery(sql.toString());

				if (rs.next()) {
					temAcesso = rs.getBoolean("acesso");
				}

				rs.close();

				closseConect(conn, stmt);

			} catch (Exception e) {
				e.printStackTrace();
			}

			closseConect(conn, stmt);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return temAcesso;
	}

	public boolean isAcessoFormularioSistema(long idUsuarioEntidade, String formulario, HttpServletRequest request) {

		boolean temAcesso = true; //futuramente alterar para false

		/*try {

			Connection conn = Conexao.getConexao(request);
			Statement stmt = Conexao.getBanco(conn);

			try {

				StringBuilder sql = new StringBuilder();

				sql.append(" SELECT p.codpermissao, p.acesso FROM int_permissao p");
				sql.append(" INNER JOIN int_formulario f ON f.codformulario=p.codformulario");
				sql.append(" WHERE p.usuarioentidade=").append(idUsuarioEntidade);
				sql.append(" AND f.formulario='").append(formulario).append("'");

				ResultSet rs = stmt.executeQuery(sql.toString());

				if (rs.next()) {
					temAcesso = rs.getBoolean("acesso");
				}

				rs.close();

				closseConect(conn, stmt);

			} catch (Exception e) {
				e.printStackTrace();
			}

			closseConect(conn, stmt);

		} catch (Exception e) {
			e.printStackTrace();
		}*/

		return temAcesso;
	}

	private void closseConect(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
		}
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
		}
	}

}
