package org.primefaces.util.acesso;

import java.time.LocalDateTime;

import org.primefaces.apollo.entidades.empresa.Empresa;

/**
 * 
 * @author willian
 *
 */
public abstract class UserAndDate {

	public abstract void setUser(Integer codigo);

	public abstract void setDateTime(LocalDateTime dateTime);

	public abstract void setEmpresaAcesso(Empresa emp);

	public void iniciarDados() {
		try {

			HttpSessionFactory sf = new HttpSessionFactory();
			Object o = sf.getAttributeSession("logado");
			if (o == null) {
				setUser(0);
			} else {
				sf.getAttributeSession("User");
				// TODO: lógica deve ser tratada para pegar código do usuário após criação da
				// entidade Usuario

				setUser(0);
			}

			Object entAcesso = sf.getAttributeSession("EmpresaAcessoSessao");
			if (entAcesso == null) {
				setEmpresaAcesso(null);
			} else {
				Empresa ent = (Empresa) sf.getAttributeSession("EmpresaAcessoSessao");
				if (ent != null && ent.getCodigo() > 0) {
					setEmpresaAcesso(ent);
				} else {
					setEmpresaAcesso(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDateTime(LocalDateTime.now());
	}

}