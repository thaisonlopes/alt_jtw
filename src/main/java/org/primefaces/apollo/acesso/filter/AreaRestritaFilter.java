package org.primefaces.apollo.acesso.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.primefaces.apollo.acesso.util.LoginUsuario;

@WebFilter(filterName = "AreaRestritaFilter", urlPatterns = "/alt/")
public class AreaRestritaFilter implements Filter {

	private static final long serialVersionUID = 4003043301582117251L;

	private static final boolean debug = false;

	private FilterConfig filterConfig = null;

	private LoginUsuario loginUsuario;
	private boolean redirecionar = true;
	private String contextPath = "";

	/**
	 * Default constructor.
	 */
	public AreaRestritaFilter() {

	}

	private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {

		redirecionar = true;
		loginUsuario = null;

		if (debug) {
			log("AreaRestritaFilter:DoBeforeProcessing");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = (HttpSession) httpRequest.getSession(false);

		contextPath = httpRequest.getContextPath();

		if (session != null) {

			if (session.getAttribute("UsuarioLogado") != null) {
				loginUsuario = (LoginUsuario) session.getAttribute("UsuarioLogado");
			}

			if (loginUsuario != null) {
				if (loginUsuario.isLogado()) {
					redirecionar = false;
				}
			}

		}

	}

	private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		if (debug) {
			log("AreaRestritaFilter:DoAfterProcessing");
		}

	}

	/**
	 * Retorne o objeto de configuração do filtro.
	 *
	 * @return
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Defina o objeto de configuração de filtro para este filtro.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (debug) {
			log("AreaRestritaFilter:doFilter()");
		}

		doBeforeProcessing(request, response);

		Throwable problem = null;
		try {

			if (redirecionar) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				String url = httpResponse.encodeRedirectURL(contextPath + "/login.xhtml");
				httpResponse.sendRedirect(url);
				return;

			} else {
				chain.doFilter(request, response);
			}

		} catch (IOException | ServletException t) {
			problem = t;
		}

		doAfterProcessing(request, response);

		// If there was a problem, we want to rethrow it if it is
		// a known type, otherwise log it.
		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
		if (filterConfig != null) {
			if (debug) {
				log("AreaRestritaFilter:Initializing filter");
			}
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("AreaRestritaFilter()");
		}
		StringBuilder sb = new StringBuilder("AreaRestritaFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);

		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); // NOI18N

				// PENDENTE! Localize isso para o próximo lançamento oficial
				pw.print("<h1>O recurso não foi processado corretamente</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>"); // NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (IOException ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (IOException ex) {
			}
		}
	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (IOException ex) {
		}
		return stackTrace;
	}

	public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

}
