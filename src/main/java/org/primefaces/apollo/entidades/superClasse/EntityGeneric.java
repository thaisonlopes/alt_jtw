package org.primefaces.apollo.entidades.superClasse;

import java.time.LocalDateTime;
import org.primefaces.util.acesso.UserAndDate;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

/**
 * 
 * @author willian
 *
 */
@MappedSuperclass
public class EntityGeneric implements java.io.Serializable {

	private static final long serialVersionUID = 142331852766878778L;

	@Column(name = "dthr_create")
	private LocalDateTime dthr_create;

	@Column(name = "cod_usuario_create")
	private int cod_usuario_create;

	@Column(name = "dthr_update")
	private LocalDateTime dthr_update;

	@Column(name = "cod_usuario_update")
	private int cod_usuario_update;

	@Version
	private Integer version;

	public LocalDateTime getDthr_create() {
		return dthr_create;
	}

	public void setDthr_create(LocalDateTime dthr_create) {
		this.dthr_create = dthr_create;
	}

	public int getCod_usuario_create() {
		return cod_usuario_create;
	}

	public void setCod_usuario_create(int cod_usuario_create) {
		this.cod_usuario_create = cod_usuario_create;
	}

	public LocalDateTime getDthr_update() {
		return dthr_update;
	}

	public void setDthr_update(LocalDateTime dthr_update) {
		this.dthr_update = dthr_update;
	}

	public int getCod_usuario_update() {
		return cod_usuario_update;
	}

	public void setCod_usuario_update(int cod_usuario_update) {
		this.cod_usuario_update = cod_usuario_update;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@PrePersist
	protected void prePersist() {
		try {

			UserAndDate userAndDate = new UserAndDate() {

				@Override
				public void setUser(Integer codigo) {
					cod_usuario_create = codigo;
					cod_usuario_update = codigo;
				}

				@Override
				public void setDateTime(LocalDateTime dateTime) {
					dthr_create = dateTime;
					dthr_update = dateTime;
				}

			};
			userAndDate.iniciarDados();

		} catch (Exception e) {
		}
	}

	@PreUpdate
	protected void preUpdateDadosUsuarioDataLanc() {
		try {

			UserAndDate userAndDate = new UserAndDate() {

				@Override
				public void setUser(Integer codigo) {
					cod_usuario_update = codigo;
				}

				@Override
				public void setDateTime(LocalDateTime dateTime) {
					dthr_update = dateTime;
				}

			};
			userAndDate.iniciarDados();

		} catch (Exception e) {
		}
	}
}