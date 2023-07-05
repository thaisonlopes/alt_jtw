package org.primefaces.util.acesso;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author willian
 *
 */
public class HttpSessionFactory {

	HttpSession session = null;

    public HttpSessionFactory() {
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null) {
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        }
    }

    public Object getAttributeSession(String attribute) {
        if (session != null) {
            return session.getAttribute(attribute);
        } else {
            return null;
        }
    }
}