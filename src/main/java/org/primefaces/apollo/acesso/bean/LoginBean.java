package org.primefaces.apollo.acesso.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.primefaces.apollo.acesso.dto.BancoDTO;
import org.primefaces.apollo.acesso.util.RecuperarSubDominio;
import org.primefaces.apollo.entidades.empresa.Empresa;
import org.primefaces.apollo.entidades.usuarios.usuario.Usuario;
import org.primefaces.util.base.TextoUtil;
import org.primefaces.util.conexao.Conexao;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 839163167039487634L;
	
	private String dominio;
	private BancoDTO bancoDTO = new BancoDTO();
	private String usuario = "";
	private String senha = "";
	private Integer idBanco = 0;
	private Integer codEmpresa = 0;

	private ExternalContext context;
	private HttpServletRequest request;

	private List<BancoDTO> listaBancos = new ArrayList<BancoDTO>();
	private List<SelectItem> listaEmpresa;

	public BancoDTO getBancoDTO() {
		return bancoDTO;
	}

	public void setBancoDTO(BancoDTO bancoDTO) {
		this.bancoDTO = bancoDTO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public List<BancoDTO> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<BancoDTO> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public Integer getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(Integer codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public List<SelectItem> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<SelectItem> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	@PostConstruct
	public void init() {
		try {

			context = FacesContext.getCurrentInstance().getExternalContext();
			request = (HttpServletRequest) context.getRequest();

			HttpSession session = (HttpSession) context.getSession(false);
			if (session != null) {
				session.removeAttribute("UsuarioLogado");
				session.invalidate();
			}

			dominio = RecuperarSubDominio.getSubdominio(request);

			loadDataBaseConf(dominio);

			if (listaEmpresa == null) {
				listaEmpresa = new ArrayList<SelectItem>();
			}

			listaEmpresa.clear();
			codEmpresa = 0;

			changedBanco();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadDataBaseConf(String dominio) {
		try {

			if (ControleAcesso.isAmbienteDeProducao()) {

			} else {

				// --Parte para acesso local
				BancoDTO banco = new BancoDTO();
				banco.setCodigo(1);
				banco.setDataSource("SIGPWEB_PM_VARZEA_DA_PALMA_PD");
				banco.setEmpresa("Prefeitura De Teste");
				banco.setNomeDB("sigp_varzeadapalma");
				banco.setSenhaDB("427623");
				banco.setUrlDB("jdbc:postgresql://localhost:5432/sigp_varzeadapalma");
				banco.setUsuarioDB("postgres");
				listaBancos = new ArrayList<BancoDTO>();
				listaBancos.add(banco);

				if (listaBancos != null && !listaBancos.isEmpty()) {
					idBanco = listaBancos.get(0).getCodigo();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changedBanco() {
		try {

			if (listaBancos != null && !listaBancos.isEmpty()) {

				for (int i = 0; i < listaBancos.size(); i++) {

					if (listaBancos.get(i).getCodigo().equals(idBanco)) {

						if (ControleAcesso.isAmbienteDeProducao()) {

							request.getSession().setAttribute("PUDATABASE", listaBancos.get(i).getNomeDB());
							request.getSession().setAttribute("PUDATASOURCE", listaBancos.get(i).getDataSource());
							request.getSession().setAttribute("PUURL", listaBancos.get(i).getUrlDB());
							request.getSession().setAttribute("PUUSUARIO", listaBancos.get(i).getUsuarioDB());
							request.getSession().setAttribute("PUSENHA", listaBancos.get(i).getSenhaDB());
							request.getSession().setAttribute("PUPERSISTENCIA", "SIGPINTEGRADO");
							request.getSession().setAttribute("NOMEENTIDADE", listaBancos.get(i).getEmpresa());

						} else {

							request.getSession().setAttribute("PUDATABASE", listaBancos.get(i).getNomeDB());
							request.getSession().setAttribute("PUDATASOURCE", listaBancos.get(i).getDataSource());
							request.getSession().setAttribute("PUURL", listaBancos.get(i).getUrlDB());
							request.getSession().setAttribute("PUUSUARIO", listaBancos.get(i).getUsuarioDB());
							request.getSession().setAttribute("PUSENHA", listaBancos.get(i).getSenhaDB());
							request.getSession().setAttribute("PUPERSISTENCIA", "SIGPINTEGRADO");
							request.getSession().setAttribute("NOMEENTIDADE", listaBancos.get(i).getEmpresa());
						}

						break;

					}

				}

			}

			iniciarListaEntidade();

		} catch (Exception e) {
			e.printStackTrace();
			// setMensagemERRO("Ocorreu um erro ao tentar efetuar login no sistema. Erro: "
			// + e.getMessage());
		}
	}

	private void iniciarListaEntidade() {
		try {

			if (listaEmpresa == null) {
				listaEmpresa = new ArrayList<SelectItem>();
			}

			listaEmpresa.clear();

			Connection conn = null;
			Statement stmt = null;

			try {

				if (idBanco != null && idBanco > 0) {

					conn = Conexao.getConexao();
					stmt = Conexao.getBanco(conn);

					ResultSet rs = stmt.executeQuery("SELECT codigo, nome FROM empresa ORDER BY codigo");

					while (rs.next()) {
						if (rs.getInt("codigo") > 0) {
							listaEmpresa.add(new SelectItem(rs.getInt("codigo"), rs.getString("nome")));
						}
					}

					rs.close();

					closseConect(conn, stmt);

				}

			} catch (Exception e) {
				e.printStackTrace();
				// setMensagemERRO("Não foi possível determinar as entidades ou órgãos da
				// cidade.");
			} finally {
				closseConect(conn, stmt);
			}

			codEmpresa = 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doLogin() {
		try {

			validarDados();

			Empresa empresa = null; // entidadeDAO.get(codEntidade);
			if (empresa == null) {
				// throw new Exception("Empresa inválida.");
			}

			Usuario us;

			// Pesquisa o usuário
			us = null;

			if (TextoUtil.soDigitos(usuario.trim()).length() >= 11) {
				us = null; // usuarioDAO.getPorCPF(usuario.trim());
			}

			/*
			 * if (us != null) {
			 * 
			 * boolean temAcessoAEntidade = false;
			 * 
			 * // Verificar qualo tipo de acesso do usuario if (us.getTipoAcesso() != null)
			 * { if (us.getTipoAcesso().getCodigo() < 20) {// Acesso restrito a entidade if
			 * (us.getTipoAcesso().getCodigo() > 9) {// Valores menores que 9 não tem acesso
			 * usE = usuarioEntidadeDAO.getPorUserEnt(us.getCodigo(), entidade.getCodigo());
			 * if (usE != null) { int tipoPermicaoAcesso =
			 * usE.getTipoPermissao().getCodigo(); if (tipoPermicaoAcesso > 5) {
			 * temAcessoAEntidade = true; } else if (tipoPermicaoAcesso <= 5) { if
			 * (isAcessoModuloSistema(usE.getId(), MODULO_SISTEM)) { temAcessoAEntidade =
			 * true; } else { throw new Exception("Usuário não tem permissão de acesso."); }
			 * } else { throw new Exception("Usuário não tem permissão de acesso."); } }
			 * else { throw new Exception("Usuário não tem permissão de acesso."); }
			 * 
			 * } else { throw new Exception("Usuário não tem permissão de acesso."); } }
			 * else { temAcessoAEntidade = true; } } else { throw new
			 * Exception("Usuário não tem permissão de acesso."); }
			 * 
			 * if (temAcessoAEntidade) {
			 * 
			 * if (us.isAutenticacaoValida(senha.trim())) {
			 * 
			 * LoginUsuario log = new LoginUsuario(); log.setEntidade(entidade);
			 * log.setAnoExercicio(anoExercicio); log.setUsuario(us);
			 * log.setUsuarioEntidade(usE); log.setSiglaParam(dominio); log.setLogado(true);
			 * 
			 * context = FacesContext.getCurrentInstance().getExternalContext();
			 * HttpServletResponse response = (HttpServletResponse) context.getResponse();
			 * HttpServletRequest request = (HttpServletRequest) context.getRequest();
			 * request.getSession().setAttribute("UsuarioLogado", log);
			 * request.getSession().setAttribute("AnoSessao", anoExercicio);
			 * request.getSession().setAttribute("EntidadeAcessoSessao", log.getEntidade());
			 * request.getSession().setAttribute("UsuarioSessao", log.getUsuario());
			 * 
			 * response.sendRedirect("sistema/index.xhtml?faces-redirect=true");
			 * 
			 * } else { throw new Exception("Usuário inválido."); }
			 * 
			 * } else { throw new Exception("Acesso negado.");
			 * 
			 * }
			 * 
			 * } else { throw new Exception("Usuário inválido.");
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			// setMensagemERRO("Ocorreu um erro ao tentar efetuar login. Erro: " +
			// e.getMessage());
		}
	}

	private void validarDados() throws Exception {

		if (usuario == null || usuario.trim().length() < 3) {
			throw new Exception("Usuário inválido.");
		}

		if (senha == null || senha.trim().length() <= 1) {
			throw new Exception("Senha inválida.");
		}

		if (idBanco == null || idBanco <= 0) {
			throw new Exception("Banco de dados para a cidade não encontrado.");
		}

		if (codEmpresa == null || codEmpresa <= 0) {
			throw new Exception("Entidade ou órgão inválido.");
		} 

	}

	public String logoff() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("UsuarioLogado");
		session.invalidate();
		return "/login.xhtml?faces-redirect=true";
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
