package org.primefaces.util.acesso;

import java.time.LocalDateTime;

/**
 * 
 * @author willian
 *
 */
public abstract class UserAndDate {

	public abstract void setUser(Integer codigo);

	public abstract void setDateTime(LocalDateTime dateTime);

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

		} catch (Exception e) {
			e.printStackTrace();
		}

		setDateTime(LocalDateTime.now());
	}

}